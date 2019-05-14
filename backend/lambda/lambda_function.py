import boto3
import json
import uuid
import decimal
from datetime import datetime
from boto3.dynamodb.conditions import Key

# Helper class to convert a DynamoDB item to JSON
class DecimalEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, decimal.Decimal):
            if o % 1 > 0:
                return float(o)
            else:
                return int(o)
        return super(DecimalEncoder, self).default(o)

def respond(err, response=None):
    response = {
        'statusCode': 200,
        'body': json.dumps(response),
        'headers': {
            'Content-Type': 'application/json'
        }
    }

    return response

def get_conditions():
    conditionsTable = boto3.resource('dynamodb').Table('MedicalConditions')
    x = conditionsTable.scan(AttributesToGet=['name','id'])['Items']
    body = []
    for i in x: 
        item = { 'id': str(i['id']), 'name': str(i['name']) }
        body.append(item)

    resp =  {
        'statusCode': 200,
        'body': json.dumps(body),
        'headers': {
            'Content-Type': 'application/json'
        }
    }

    return resp

def get_treatment(condID):
    '''Return the treatment for the given condition'''
    conditionsTable = boto3.resource('dynamodb').Table('MedicalConditions')

    condition = conditionsTable.get_item(
        Key={
            'id': condID
        }
    )

    name = condition['Item']['name']
    treatment = condition['Item']['treatment']

    resp = {
        'name': str(name),
        'treatment': str(treatment)
    }

    return respond(200, resp)


def lambda_handler(event, context):
    path, method = event['path'], event['httpMethod']

    if method not in ['GET']:
        return respond('Lambda function only supports GET requests for the moment.')
    elif (path == '/treatment'):
        return get_treatment(int(event['queryStringParameters']['id']))
    elif (path == '/conditions'):
        return get_conditions()
    else:
        return {
            "statusCode": 200,
            "body": json.dumps('Hello from Lambda!')
        }

    
