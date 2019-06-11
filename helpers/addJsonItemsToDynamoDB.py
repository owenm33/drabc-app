# -*- coding: utf-8 -*-
"""
Author: Owen Morgan
Date: 15/02/2019
"""

import boto3 
import json

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('MedicalConditions')

with open('conditions.json') as json_data:
    data = json.load(json_data,)
    
for item in data:
    table.put_item(
            Item={
                    'id': int(item['id']),
                    'name': item['name'],
                    'treatment': item['treatment']
                    }
            )
    
