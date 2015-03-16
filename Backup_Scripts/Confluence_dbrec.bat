set /p db_date=请输入恢复至日期（例如：20151231）
mysql -h 22.18.61.194 -u root -proot confluence < F:\Backup\Confluence\Database\Data_Dump\confluencedb_%db_date%.sql