set /p db_date=������ָ������ڣ����磺20151231��
mysql -h 22.18.61.194 -u root -proot jiradb < F:\Backup\JIRA\Database\Data_Dump\jiradb_%db_date%.sql