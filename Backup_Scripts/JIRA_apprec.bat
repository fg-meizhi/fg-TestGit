set /p app_date=请输入恢复至日期（例如：20151231）
xcopy "F:\Backup\JIRA\Program\%app_date%" "E:\Programs\JIRA" /Y /S /E