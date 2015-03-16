set /p appdata_date=请输入恢复至日期（例如：20151231）
xcopy "F:\Backup\JIRA\JIRA_data\%appdata_date%" "E:\Programs\JIRA_data" /Y /S /E