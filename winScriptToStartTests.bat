title "selenoid tests"
:: стартуем докер
:: запускаем selenoid командой "./cm selenoid start --vnc" из директории селеноида
:: НО... у меня WIN 10 home, т.е. докер стартует автоматически на старте системы и сам запускает селеноид

@echo
rem go to project folder
cd C:\OtusProjects\OtusQAAutomationHomeTasks
rem run tests with maven command + browser param "remote"
call mvn clean test -Dbrowser=remote

pause