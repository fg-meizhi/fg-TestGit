md F:\Backup\Mysql_data_backup\194\%date:~,4%%date:~5,2%%date:~8,2%
md F:\Backup\Mysql_data_backup\194\%date:~,4%%date:~5,2%%date:~8,2%\jiradb
md F:\Backup\Mysql_data_backup\194\%date:~,4%%date:~5,2%%date:~8,2%\confluence

xcopy "C:\ProgramData\MySQL\MySQL Server 5.5\data\jiradb" F:\Backup\Mysql_data_backup\194\%date:~,4%%date:~5,2%%date:~8,2%\jiradb\ /Y/D
xcopy "C:\ProgramData\MySQL\MySQL Server 5.5\data\confluence" F:\Backup\Mysql_data_backup\194\%date:~,4%%date:~5,2%%date:~8,2%\confluence\ /Y/D
xcopy "E:\Programs\MySQL\my.ini" F:\Backup\Mysql_data_backup\194\%date:~,4%%date:~5,2%%date:~8,2%\ /Y