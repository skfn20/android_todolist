아이디	: *********
비밀번호 : ****

[MainActivity]
처음에 아이디와 비밀번호를 입력하고 둘 다 일치한 경우 들어갈 수 있다.
비밀번호 입력창에서 엔터를 치면 입력 창이 닫히고 밑에 버튼이 눌린다.
만약 일치하지 않는다면 버튼 밑에 빨간 글씨로 일치하지 않았음이 표시된다.

[TodoActivity]
Task, Doing, Done으로 들어갈 수 있는 버튼 처리된 텍스트뷰가 있고 커서가 올라가 있을 시 뒤에
배경이 주황색으로 변한다.

[Task] [Doing] [Done]
들어가면 제목과 밑에 항목을 추가할 버튼이 있다. 제목을 클릭하면 다른 목록의 메뉴바가 뜨고 
클릭하면 다른 목록으로 이동할 수 있다. 
예) Task -> Doing, Done 클릭 시 해당 목록으로 이동
추가 버튼을 누르면 AddCard activity로 이동하고 AddCard에서 Add버튼을 누르면 항목이 추가됨
추가된 항목은 
|Job|		|move	|    |edit	  |   |delete|
|Deadline|	|button	|    |button|   |button|
다음과 같이 구성되어있다. 
mov 버튼을 클릭 시 -> title을 클릭했을 때처럼 목록의 메뉴바가 뜨고 클릭하면 항목이 
		      해당 목록으로 이동
edit 버튼 클릭 시 -> 수정
delete 버튼 클릭 시  -> 삭제 

항목들은 Application class를 상속받은 MyApplication class에 있는 task_list, doing_list, done_list에
저장된다. 
