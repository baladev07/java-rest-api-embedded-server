import csv
from pathlib import Path
from datetime import datetime
import requests

url2 = "https://accounts.zoho.com/oauth/v2/token?client_id=1000.Y6HLN2R9VW9WMWNT7522KN0K8HRD8K&client_secret" \
       "=7980b011c18086917cc05b2d75c48bc6a4572aa709&redirect_uri=https://notto.com&grant_type=refresh_token" \
       "&refresh_token=1000.5bb735e789cbb42b17232cf5c5ad0017.0723c593de397a4aeaa4b59c74e3e592"
access_token = requests.post(url2).json().get('access_token')
current_data = str(datetime.date(datetime.today()))
file_path = "" + str(
    Path.home()) + "/Downloads/apache-jmeter-5.4.1/bin/examples/jmeter_aggregate_csv/" + current_data + ".csv"
logs_path = "" + str(Path.home()) + "/Downloads/apache-jmeter-5.4.1/bin/123.csv"
with open(file_path, 'r') as file:
    data = csv.reader(file)
    file_len = len(list(data))

param = {}


def fetch_build_label():
    url = "https://expense.localzoho.com/api/v1/userpreferences/editpage?organization_id=66564612&authtoken" \
          "=857343e0aabd957cf77a4471f579b0a6 "
    response = requests.get(url)
    return response.headers.get('SERVER_BUILD_VERSION')


def pushData(param):
    try:
        url = "https://analyticsapi.zoho.com/api/nitharsana.r@zohocorp.com/ZE Test/Sample Table"  # ZOHO_ACTION=ADDROW&ZOHO_OUTPUT_FORMAT=XML&ZOHO_ERROR_FORMAT=XML&ZOHO_API_VERSION=1.0&Date=&Build Label=&API Label=&Min=&Max=&Average= &Error%=&responseCode=&responseMessage=&Duration Assertion=&Assertion Failure Message="
        header = {"Authorization": "Zoho-oauthtoken " + access_token + ""}
        response = requests.post(url, headers=header, params=param)
        print(response.status_code, response.content)
    except Exception as e:
        print(e)


param['ZOHO_ACTION'] = 'ADDROW'
param['ZOHO_OUTPUT_FORMAT'] = 'JSON'
param['ZOHO_ERROR_FORMAT'] = 'JSON'
param['ZOHO_API_VERSION'] = '1.0'
param['Date'] = ''
param['Build Label'] = ''
param['API Label'] = ''
param['Min'] = ''
param['Max'] = ''
param['Average'] = ''
param['Error%'] = ''
param['Samples'] = ' '
# # pushData(param)
with open(file_path, 'r') as file, open(logs_path, 'r') as log_file:
    data = csv.reader(file)
    log_data = csv.reader(log_file)
    param['Date'] = current_data
    for i, row in enumerate(data):
        if file_len - 1 > i > 1:
            param['API Label'] = row[0]
            param['Min'] = row[7]
            param['Max'] = row[8]
            param['Average'] = row[2]
            param['Error%'] = row[9]
            param['Samples'] = ""
            param['Build Label'] = fetch_build_label
            for log in log_data:
                if row[0] == log[2]:
                    param['responseCode'] = log[3]
                    param['Assertion Status'] = log[8]
                    print(log[8])
                    # pushData(param)
                    break
    file.close()
    log_file.close()

print("Data pushed to analytics")
