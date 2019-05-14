# -*- coding: utf-8 -*-
"""
Created on Wed May 15 05:32:33 2019

@author: owenm
"""

import os, json

path = os.getcwd()

json_files = [pos_json for pos_json in sorted(os.listdir(path)) if pos_json.endswith('.json')]
conditions = []

for index, js in enumerate(json_files):
    with open(os.path.join(path, js), encoding="utf8") as json_file:
        json_text = json.load(json_file)
        condition = {}
        condition['id'] = js.split()[2]
        condition['name'] = json_text['Name']
        condition['treatment'] = json_text['TreatmentDescription']
        conditions.append(condition)
   


with open('conditions.json', 'w') as outfile:
    json.dump(conditions, outfile, indent=4)
