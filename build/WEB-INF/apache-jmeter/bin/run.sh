#!/bin/bash
echo "Starting....."
current_date=$(date "+%Y-%m-%d")
#echo ${current_time}
#touch "${current_time}".jtl
cd examples/jmeter_logs
touch "${current_date}".jtl
cd ../jmeter_aggregate_csv/
touch "${current_date}".csv
cd ../../
sh jmeter -n -t ${HOME}/IdeaProjects/project/apache-jmeter/bin/ZE_Expense_GET.jmx -l ${HOME}/IdeaProjects/project/apache-jmeter/bin/examples/jmeter_logs/${current_date}.jtl
cd ../lib/
java -jar cmdrunner-2.0.jar --tool Reporter --generate-csv "${HOME}/IdeaProjects/project/apache-jmeter/bin/examples/jmeter_aggregate_csv/${current_date}.csv" --input-jtl "${HOME}/IdeaProjects/project/apache-jmeter/bin/examples/jmeter_logs/${current_date}.jtl" --plugin-type AggregateReport
#python3 jmeter.py
echo "completed"
