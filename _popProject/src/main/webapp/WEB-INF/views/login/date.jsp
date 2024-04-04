<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calendar with Schedule</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        .schedule {
            background-color: #ffd700;
            position: relative;
        }

        .delete-btn {
            position: absolute;
            top: 0;
            right: 0;
            background-color: #f44336;
            color: white;
            border: none;
            padding: 4px 8px;
            cursor: pointer;
            font-size: 12px;
            border-radius: 50%;
        }
    </style>
</head>
<body>
    <h2>Calendar with Schedule</h2>
    <div id="calendar"></div>

    <!-- 스케줄 추가 입력 폼 -->
    <div>
        <label for="scheduleDate">날짜:</label>
        <input type="date" id="scheduleDate">
        <label for="scheduleTitle">제목:</label>
        <input type="text" id="scheduleTitle">
        <button onclick="addSchedule()">추가</button>
    </div>

    <script>
        // 현재 날짜 정보 가져오기
        var currentDate = new Date();
        var currentYear = currentDate.getFullYear();
        var currentMonth = currentDate.getMonth(); // 월은 0부터 시작함

        // 스케줄 데이터
        var scheduleData = {};

        // 달력 생성 함수 호출
        createCalendar(currentYear, currentMonth);

        function createCalendar(year, month) {
            // 해당 월의 첫째 날과 마지막 날 구하기
            var firstDay = new Date(year, month, 1);
            var lastDay = new Date(year, month + 1, 0);
            var daysInMonth = lastDay.getDate();

            // 달력 테이블 생성
            var calendarHtml = '<table>';
            calendarHtml += '<tr><th colspan="7">' + year + '년 ' + (month + 1) + '월</th></tr>';
            calendarHtml += '<tr><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr>';
            
            // 달력 내용 채우기
            var dateCount = 1;
            for (var i = 0; i < 6; i++) { // 최대 6주
                calendarHtml += '<tr>';
                for (var j = 0; j < 7; j++) { // 7일 (일 ~ 토)
                    var currentDate = new Date(year, month, dateCount);
                    var dateString = currentDate.toISOString().split('T')[0]; // YYYY-MM-DD 형식으로 변환
                    var schedule = scheduleData[dateString];
                    if (i === 0 && j < firstDay.getDay()) { // 첫째 주의 첫째 날 이전은 빈 칸으로 채우기
                        calendarHtml += '<td></td>';
                    } else if (dateCount > daysInMonth) { // 마지막 날 이후는 빈 칸으로 채우기
                        calendarHtml += '<td></td>';
                    } else {
                        if (schedule) {
                            calendarHtml += '<td class="schedule" title="' + schedule + '">' + dateCount + '<button class="delete-btn" onclick="deleteSchedule(\'' + dateString + '\')">X</button></td>';
                        } else {
                            calendarHtml += '<td>' + dateCount + '</td>';
                        }
                        dateCount++;
                    }
                }
                calendarHtml += '</tr>';
            }
            calendarHtml += '</table>';

            // 생성된 달력을 화면에 출력
            document.getElementById('calendar').innerHTML = calendarHtml;
        }

        // 스케줄 추가 함수
        function addSchedule() {
            var scheduleDate = document.getElementById('scheduleDate').value;
            var scheduleTitle = document.getElementById('scheduleTitle').value;
            if (!scheduleDate || !scheduleTitle) {
                alert('날짜와 제목을 입력하세요.');
                return;
            }
            scheduleData[scheduleDate] = scheduleTitle;
            createCalendar(currentYear, currentMonth); // 달력 다시 그리기
        }

        // 스케줄 삭제 함수
        function deleteSchedule(dateString) {
            delete scheduleData[dateString];
            createCalendar(currentYear, currentMonth); // 달력 다시 그리기
        }
    </script>
</body>
</html>