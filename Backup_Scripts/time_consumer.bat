rem ʱ������ ���㲿��
rem =============================
@echo off
set /a starttime=(%time:~0,2%*3600)+(%time:~3,2%*60)+%time:~6,2%

rem ===========================================================
rem ִ��һЩ��ʱ����Ϊ���磺mysqldump, xcopy������ϵͳʱ��ȣ�
pause
rem ===========================================================

set /a consumer=(%time:~0,2%*3600)+(%time:~3,2%*60)+%time:~6,2%-%starttime%
set /a hour=%consumer%/3600
set /a min=(%consumer%%%3600)/60
set /a sec=(%consumer%%%3600)%%60
echo �ܺ�ʱ��%hour%Сʱ%min%��%sec%��