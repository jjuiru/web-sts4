<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
</head>
<body>
    <h1>마이페이지</h1>
    <ul>
        <li><a href="#" onclick="showTab('userInfo')">내 정보</a></li>
        <li><a href="#" onclick="showTab('myPopups')">나의 팝업</a></li>
    </ul>

    <!-- 내 정보 -->
    <div id="userInfo" style="display: none;">
        <h2>내 정보</h2>
        <div>
            <!-- 여기에 내 정보 입력 양식을 넣으세요 -->
        </div>
    </div>

    <!-- 나의 팝업 -->
    <div id="myPopups" style="display: none;">
        <h2>나의 팝업</h2>
        <div>
            <!-- 여기에 나의 팝업 리스트를 출력하는 코드를 넣으세요 -->
        </div>
    </div>

    <script>
        function showTab(tabName) {
            // 모든 탭 숨기기
            var tabs = document.querySelectorAll('div[id^="tab"]');
            tabs.forEach(function(tab) {
                tab.style.display = 'none';
            });

            // 선택한 탭 보여주기
            document.getElementById(tabName).style.display = 'block';
        }
    </script>

    <script>
        // 페이지 로드 시 첫 번째 탭 보여주기
        window.onload = function() {
            showTab('userInfo'); // 초기에 내 정보 탭을 보여줍니다.
        };
    </script>
</body>
</html>