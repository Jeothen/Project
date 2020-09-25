import csv
import pprint
import json
import requests
API_URL = 'http://localhost:8000/api/'
headers = {'content-type': 'application/json'}

f = open('Item_2.csv', 'r', encoding='utf-8')
rdr = list(csv.reader(f))
# print(rdr)
entries = {'ratings':[]}
for line in rdr[1::]:
  # print(line)
  fields={}
  for i in range(1,len(rdr[0])) :
    fields[rdr[0][i]]=line[i]
  # row_dict = {}
  # row_dict['pk'] = int(line[0])
  # row_dict['model'] = 'api.Blend'
  # row_dict['fields'] = fields
  # print(row_dict)
  entries['ratings'].append(fields)
# print(entries)
f.close()
print(entries)
response = requests.post(API_URL + 'Flavor/get/', data=json.dumps(entries,ensure_ascii=False).encode("utf-8"), headers=headers)
print(response.text)
