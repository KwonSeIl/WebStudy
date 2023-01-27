<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
	margin-top: 30px;
}
.row{
	width: 100%;
	margin: 0px auto;
}
</style>
<script type="text/javascript">
// {} => VO, [{},{}] => ArrayList
let movies=[
	{"startYearDate":"2023.01.24","endYearDate":"2023.01.24","startDate":"2023년 01월 24일(화)","endDate":"2023년 01월 24일(화)","movieCd":"20190808","showDt":"20230124","thumbUrl":"/common/mast/movie/2022/12/thumb/thn_115acdd9e2844399b21c49e67718919c.jpg","movieNm":"교섭","movieNmEn":"The Point Men","synop":"“어떤 경우라도 희생자를 안 만드는 게 이 협상의 기조 아닙니까?”\r\n\r\n분쟁지역 아프가니스탄에서 한국인들이 탈레반에게 납치되는 최악의 피랍사건이 발생한다.\r\n교섭 전문이지만 아프가니스탄은 처음인 외교관 재호(황정민)가 현지로 향하고,\r\n국정원 요원 대식(현빈)을 만난다.\r\n원칙이 뚜렷한 외교관과 현지 사정에 능통한 국정원 요원.\r\n입장도 방법도 다르지만, 두 사람은 인질을 살려야 한다는 목표를 향해 함께 나아간다.\r\n살해 시한은 다가오고, 협상 상대, 조건 등이 시시각각 변하는 상황에서\r\n교섭의 성공 가능성은 점점 희박해져 가는데...","prdtYear":"2022","indieYn":null,"artmovieYn":null,"multmovieYn":null,"showTm":"108","showTs":"3","director":"임순례","prNm":"(주)영화사수박,원테이크필름","dtNm":"플러스엠 엔터테인먼트","repNationCd":"한국","movieType":"장편","moviePrdtStat":"개봉","genre":"드라마","watchGradeNm":"12세이상관람가","openDt":"20230118","salesAmt":1973468456,"audiCnt":190290,"scrCnt":1231,"showCnt":5390,"rank":1,"rankInten":0,"rankOldAndNew":"OLD","rownum":1},
	{"startYearDate":"2023.01.24","endYearDate":"2023.01.24","startDate":"2023년 01월 24일(화)","endDate":"2023년 01월 24일(화)","movieCd":"20225061","showDt":"20230124","thumbUrl":"/common/mast/movie/2022/11/thumb/thn_87e132235b634767b9c22e8483cfbba7.jpg","movieNm":"아바타: 물의 길","movieNmEn":"Avatar: The Way of Water","synop":"<아바타: 물의 길>은 판도라 행성에서 \r\n'제이크 설리'와 '네이티리'가 이룬 가족이 겪게 되는 무자비한 위협과 \r\n살아남기 위해 떠나야 하는 긴 여정과 전투, \r\n그리고 견뎌내야 할 상처에 대한 이야기를 그렸다. \r\n \r\n월드와이드 역대 흥행 순위 1위를 기록한 전편 <아바타>에 이어\r\n제임스 카메론 감독이 13년만에 선보이는 영화로, \r\n샘 워싱턴, 조 샐다나, 시고니 위버, 스티븐 랭, 케이트 윈슬렛이 출연하고\r\n존 랜도가 프로듀싱을 맡았다.","prdtYear":"2022","indieYn":null,"artmovieYn":null,"multmovieYn":null,"showTm":"192","showTs":"24","director":"제임스 카메론","prNm":null,"dtNm":"월트디즈니컴퍼니코리아 유한책임회사","repNationCd":"미국","movieType":"장편","moviePrdtStat":"개봉","genre":"액션,어드벤처,SF,스릴러","watchGradeNm":"12세이상관람가","openDt":"20221214","salesAmt":1660795036,"audiCnt":121887,"scrCnt":909,"showCnt":2111,"rank":2,"rankInten":0,"rankOldAndNew":"OLD","rownum":2},
	{"startYearDate":"2023.01.24","endYearDate":"2023.01.24","startDate":"2023년 01월 24일(화)","endDate":"2023년 01월 24일(화)","movieCd":"20228555","showDt":"20230124","thumbUrl":"/common/mast/movie/2022/12/thumb/thn_de3eb9d23b1b49e58b5f06b96d5fa6b5.jpg","movieNm":"더 퍼스트 슬램덩크","movieNmEn":"The First Slam Dunk","synop":"전국 제패를 꿈꾸는 북산고 농구부 5인방의 꿈과 열정, 멈추지 않는 도전을 그린 영화","prdtYear":"2022","indieYn":null,"artmovieYn":null,"multmovieYn":null,"showTm":"124","showTs":"26","director":"이노우에 다케히코","prNm":null,"dtNm":"(주)넥스트엔터테인먼트월드(NEW)","repNationCd":"일본","movieType":"장편","moviePrdtStat":"개봉","genre":"애니메이션","watchGradeNm":"12세이상관람가","openDt":"20230104","salesAmt":1105119929,"audiCnt":105995,"scrCnt":1035,"showCnt":2884,"rank":3,"rankInten":0,"rankOldAndNew":"OLD","rownum":3},
	{"startYearDate":"2023.01.24","endYearDate":"2023.01.24","startDate":"2023년 01월 24일(화)","endDate":"2023년 01월 24일(화)","movieCd":"20214823","showDt":"20230124","thumbUrl":"/common/mast/movie/2022/12/thumb/thn_59f5f908a3a246bbb7a362e14406353b.jpg","movieNm":"유령","movieNmEn":"Phantom","synop":"“유령에게 고함. 작전을 시작한다”\r\n\r\n1933년, 일제강점기 경성. 항일조직 ‘흑색단’의 스파이인 ‘유령’이 비밀리에 활약하고 있다.\r\n새로 부임한 경호대장 카이토는 ‘흑색단’의 총독 암살 시도를 막기 위해\r\n조선총독부 내의 ‘유령’을 잡으려는 덫을 친다.\r\n영문도 모른 채, ‘유령’으로 의심받고 벼랑 끝 외딴 호텔에 갇힌 용의자들.\r\n총독부 통신과 감독관 쥰지, 암호문 기록 담당 차경, 정무총감 비서 유리코, \r\n암호 해독 담당 천계장, 통신과 직원 백호. 이들에게 주어진 시간은 단 하루 뿐.\r\n기필코 살아나가 동지들을 구하고 총독 암살 작전을 성공시켜야 하는 ‘유령’과\r\n무사히 집으로 돌아가고 싶은 이들 사이, 의심과 경계는 점점 짙어지는데…\r\n\r\n과연 ‘유령’은 작전에 성공할 수 있을 것인가?\r\n“성공할 때까지 멈춰서는 안 된다”\r\n","prdtYear":"2022","indieYn":null,"artmovieYn":null,"multmovieYn":null,"showTm":"132","showTs":"34","director":"이해영","prNm":"더램프(주)","dtNm":"(주)씨제이이엔엠","repNationCd":"한국","movieType":"장편","moviePrdtStat":"개봉","genre":"액션","watchGradeNm":"15세이상관람가","openDt":"20230118","salesAmt":757595783,"audiCnt":71818,"scrCnt":876,"showCnt":2854,"rank":4,"rankInten":0,"rankOldAndNew":"OLD","rownum":4},
	{"startYearDate":"2023.01.24","endYearDate":"2023.01.24","startDate":"2023년 01월 24일(화)","endDate":"2023년 01월 24일(화)","movieCd":"20196478","showDt":"20230124","thumbUrl":"/common/mast/movie/2022/12/thumb/thn_13760668f5644b739d29dd80d4b00ad8.jpg","movieNm":"영웅","movieNmEn":"Hero","synop":"어머니 ‘조마리아’(나문희)와 가족들을 남겨둔 채 고향을 떠나온 대한제국 의병대장 ‘안중근’(정성화).\r\n동지들과 함께 네 번째 손가락을 자르는 단지동맹으로 조국 독립의 결의를 다진 안중근은\r\n조선 침략의 원흉인 ‘이토 히로부미’를\r\n3년 내에 처단하지 못하면 자결하기로 피로 맹세한다.\r\n\r\n그 약속을 지키기 위해 블라디보스토크를 찾은 안중근.\r\n오랜 동지 ‘우덕순’(조재윤), 명사수 ‘조도선’(배정남),\r\n독립군 막내 ‘유동하’(이현우), 독립군을 보살피는 동지 ‘마진주’(박진주)와 함께 거사를 준비한다.\r\n\r\n한편 자신의 정체를 감춘 채 이토 히로부미에게 접근해\r\n적진 한복판에서 목숨을 걸고 정보를 수집하던 독립군의 정보원 ‘설희’(김고은)는\r\n이토 히로부미가 곧 러시아와의 회담을 위해\r\n하얼빈을 찾는다는 일급 기밀을 다급히 전한다.\r\n\r\n드디어 1909년 10월 26일,\r\n이날만을 기다리던 안중근은\r\n하얼빈역에 도착한 이토 히로부미를 향해\r\n주저 없이 방아쇠를 당긴다.\r\n\r\n현장에서 체포된 그는 전쟁 포로가 아닌 살인의 죄목으로,\r\n조선이 아닌 일본 법정에 서게 되는데…\r\n\r\n누가 죄인인가, 누가 영웅인가!","prdtYear":"2022","indieYn":null,"artmovieYn":null,"multmovieYn":null,"showTm":"120","showTs":"26","director":"윤제균","prNm":"(주)제이케이필름,(주)씨제이이엔엠","dtNm":"(주)씨제이이엔엠","repNationCd":"한국","movieType":"장편","moviePrdtStat":"개봉","genre":"드라마,뮤지컬","watchGradeNm":"12세이상관람가","openDt":"20221221","salesAmt":602693315,"audiCnt":59332,"scrCnt":837,"showCnt":1713,"rank":5,"rankInten":0,"rankOldAndNew":"OLD","rownum":5},
	{"startYearDate":"2023.01.24","endYearDate":"2023.01.24","startDate":"2023년 01월 24일(화)","endDate":"2023년 01월 24일(화)","movieCd":"20224109","showDt":"20230124","thumbUrl":"/common/mast/movie/2022/12/thumb/thn_4ff6a7ee1db94c53b19e55aa58400fa8.jpg","movieNm":"장화신은 고양이: 끝내주는 모험","movieNmEn":"Puss in Boots: The Last Wish","synop":"아홉 개의 목숨 중 단 하나의 목숨만 남은 장화신은 고양이\r\n마지막 남은 목숨을 지키기 위해 히어로의 삶 대신 반려묘의 삶을 선택한 그에게 찾아온 마지막 기회, \r\n바로 소원을 들어주는 소원별이 있는 곳을 알려주는 지도!\r\n\r\n잃어버린 목숨을 되찾고 다시 히어로가 되기를 꿈꾸는 장화신은 고양이는\r\n뜻밖에 동료가 된 앙숙 파트너 '키티 말랑손', 그저 친구들과 함께라면 모든 게 행복한 강아지 '페로'와 함께\r\n소원별을 찾기 위해 길을 떠난다.\r\n그리고 소원별을 노리는 또 다른 빌런들과 마주치게 되는데…\r\n\r\n새해를 여는 끝내주는 모험이 시작된다!","prdtYear":"2022","indieYn":null,"artmovieYn":null,"multmovieYn":null,"showTm":"102","showTs":"14","director":"조엘 크로포드","prNm":null,"dtNm":"유니버설픽쳐스인터내셔널 코리아(유)","repNationCd":"미국","movieType":"장편","moviePrdtStat":"개봉","genre":"애니메이션","watchGradeNm":"전체관람가","openDt":"20230104","salesAmt":340290323,"audiCnt":34229,"scrCnt":644,"showCnt":880,"rank":6,"rankInten":0,"rankOldAndNew":"OLD","rownum":6},
	{"startYearDate":"2023.01.24","endYearDate":"2023.01.24","startDate":"2023년 01월 24일(화)","endDate":"2023년 01월 24일(화)","movieCd":"20228895","showDt":"20230124","thumbUrl":"/common/mast/movie/2022/12/thumb/thn_9767e820582d42759e9db0a4a827a3e3.jpg","movieNm":"캐리와 슈퍼콜라","movieNmEn":"CARRIE&SUPERKOLA","synop":"내 친구 ‘콜라’에게 초능력이 생겼다…?!\r\n\r\n우주 악당 ‘스펙터’에게 쫓기던 외계인 ‘마스터’는\r\n우연히 지구에 불시착하게 되고\r\n\r\n‘캐리’의 가장 소중한 인형 ‘콜라’의 몸속에 들어가게 된다\r\n그렇게 처음 ‘슈퍼콜라’와 만나게 된 11살 캐리와 친구들!\r\n\r\n자유자재로 사물을 움직일 수 있는 초능력 덕분에\r\n‘캐리’의 하나뿐인 비밀친구가 된 ‘슈퍼 콜라’!\r\n\r\n그러나 평온함도 잠시. 무리한 초능력 사용으로\r\n‘스펙터’ 일당의 레이더에 걸린 ‘슈퍼콜라’는\r\n결국 악당의 우주선으로 잡혀가고 마는데!\r\n\r\n용감무쌍 캐리의 슈퍼파워 콜라 구출 대작전!","prdtYear":"2022","indieYn":null,"artmovieYn":null,"multmovieYn":null,"showTm":"78","showTs":"34","director":"오성윤,이춘백","prNm":"(주)캐리소프트","dtNm":"롯데컬처웍스(주)롯데엔터테인먼트","repNationCd":"한국","movieType":"장편","moviePrdtStat":"개봉","genre":"애니메이션","watchGradeNm":"전체관람가","openDt":"20230118","salesAmt":169996454,"audiCnt":17679,"scrCnt":464,"showCnt":584,"rank":7,"rankInten":0,"rankOldAndNew":"OLD","rownum":7},
	{"startYearDate":"2023.01.24","endYearDate":"2023.01.24","startDate":"2023년 01월 24일(화)","endDate":"2023년 01월 24일(화)","movieCd":"20228313","showDt":"20230124","thumbUrl":"/common/mast/movie/2022/12/thumb/thn_c8d3fa881a854254842f9e63ca69b3e6.jpg","movieNm":"오늘 밤, 세계에서 이 사랑이 사라진다 해도","movieNmEn":"Even If This Love Disappears from the World Tonight","synop":"\"카미야 토루에 대해 잊지 말 것”\r\n자고 일어나면 전날의 기억을 잃는 ‘선행성 기억상실증’에 걸린 소녀 ‘마오리’\r\n\r\n“내일의 마오리도 내가 즐겁게 해줄 거야”\r\n누구에게도 기억되지 않는 무색무취의 평범한 소년 ‘토루’\r\n\r\n매일 밤 사랑이 사라지는 세계.\r\n그럼에도 불구하고,\r\n다음 날 서로를 향한 애틋한 고백을 반복하는\r\n두 소년, 소녀의 가장 슬픈 청춘담","prdtYear":"2022","indieYn":null,"artmovieYn":null,"multmovieYn":"Y","showTm":"121","showTs":"21","director":"미키 타카히로","prNm":null,"dtNm":"(주)바이포엠스튜디오,홀리가든","repNationCd":"일본","movieType":"장편","moviePrdtStat":"개봉","genre":"멜로/로맨스","watchGradeNm":"12세이상관람가","openDt":"20221130","salesAmt":182763811,"audiCnt":16177,"scrCnt":318,"showCnt":476,"rank":8,"rankInten":0,"rankOldAndNew":"OLD","rownum":8},
	{"startYearDate":"2023.01.24","endYearDate":"2023.01.24","startDate":"2023년 01월 24일(화)","endDate":"2023년 01월 24일(화)","movieCd":"20229166","showDt":"20230124","thumbUrl":"/common/mast/movie/2022/12/thumb/thn_3a55aa43d86943afb05f2cf4e88c85ae.jpeg","movieNm":"라일 라일 크로커다일","movieNmEn":"Lyle, Lyle, Crocodile","synop":"뉴욕을 음악에 홀릴 마법이 펼쳐진다!\r\n\r\n쇼맨 헥터(하비에르 바르뎀)는 어느 날 노래하는 악어 라일(숀 멘데스)을 발견하게 되고,\r\n돈과 명예를 얻게 될 성공적인 무대를 꿈꾼다.\r\n그러나 무대공포증에 있던 라일로 인해 무대는 실패하고\r\n헥터는 라일을 홀로 도심에 남기고 떠난다.​\r\n\r\n한편, 뉴욕에 이사 온 프림 가족은 매일 밤 의문의 노랫소리로 신기한 경험을 하게 되고,\r\n그 놀라운 정체와 마주하게 되면서 인생이 바뀌게 되는데…","prdtYear":"2022","indieYn":null,"artmovieYn":null,"multmovieYn":null,"showTm":"105","showTs":"49","director":"조쉬 고든,윌 스펙","prNm":null,"dtNm":"소니픽쳐스엔터테인먼트코리아주식회사극장배급지점","repNationCd":"미국","movieType":"장편","moviePrdtStat":"개봉","genre":"가족,뮤지컬,드라마","watchGradeNm":"전체관람가","openDt":"20230118","salesAmt":83802442,"audiCnt":8563,"scrCnt":375,"showCnt":553,"rank":9,"rankInten":1,"rankOldAndNew":"OLD","rownum":9},
	{"startYearDate":"2023.01.24","endYearDate":"2023.01.24","startDate":"2023년 01월 24일(화)","endDate":"2023년 01월 24일(화)","movieCd":"20215315","showDt":"20230124","thumbUrl":"/common/mast/movie/2023/01/thumb/thn_61c9080afa464dadb22d6a46730cfefd.jpg","movieNm":"스위치","movieNmEn":"SWITCH","synop":"하룻밤 사이, 인생이 180도 뒤집어졌다!\r\n캐스팅 0순위 천만배우이자 자타공인 최고의 스캔들 메이커 ‘박강’.\r\n화려한 싱글 라이프를 만끽하고 있지만,\r\n정작 크리스마스이브에 끌어안을 것이라고는 연말 시상식 트로피뿐.\r\n유일한 친구이자 뒤처리 전문 매니저 ‘조윤’을 붙잡아 거하게 한잔하고 택시를 잡아탄다.\r\n다음날 아침, 낯선 집에서 깨어난 ‘박강’에게 생전 처음 보는 꼬맹이 둘이 안겨오고,\r\n성공을 위해 이별했던 첫사랑 ‘수현’이 잔소리를 폭격하며 등짝 스매싱을 날리는 것이 아닌가?\r\n게다가 매니저 ‘조윤’이 천만배우가 되어 그가 있어야 할 톱스타의 자리를 꿰차고 있는데…\r\n이게 무슨 황당한 시추에이션?!\r\n180도 뒤집어진 인생에 속이 뒤집어지는 ‘박강’은\r\n불현듯, 지난밤 택시 기사가 무심코 건넨 한마디가 떠오르는데…\r\n“만약에 선택을 바꿀 기회가 생긴다면, 어떻게 하시겠어요?”\r\n","prdtYear":"2021","indieYn":null,"artmovieYn":null,"multmovieYn":null,"showTm":"112","showTs":"35","director":"마대윤","prNm":"(주)하이브미디어코프","dtNm":"롯데컬처웍스(주)롯데엔터테인먼트","repNationCd":"한국","movieType":"장편","moviePrdtStat":"개봉","genre":"기타","watchGradeNm":"12세이상관람가","openDt":"20230104","salesAmt":53808291,"audiCnt":5913,"scrCnt":198,"showCnt":383,"rank":10,"rankInten":-1,"rankOldAndNew":"OLD","rownum":10}]
// 전역변수
window.onload=function(){
	//선언 => 지역변수
	let html='';
	//	movies.map((movie)=>
	movies.map(function(movie){
		html+='<tr onmouseover=detail('+movie.rank+')>'
			+'<td class="text-center">'+movie.rank+'</td>'
			+'<td class="text-center"><img src="http://www.kobis.or.kr'+movie.thumbUrl+'"width=35 height=35></td>'
			+'<td>'+movie.movieNm+'</td>'
			+'<td>'+movie.director+'</td>'
			+'<td>'+movie.genre+'</td>'
			+'</tr>'
	})
	document.querySelector('#list').innerHTML=html;	//객체모델 => 태그를 선택해서 가지고 온다:이벤트 처리, 데이터 첨부가 가능
	// 자바 => 데이터를 가지고 온다(JSON) ==> 자바스크립트 자체에서 처리
	// JSP가 변경이 되지 않는다는 장점 존재 => 같은 화면에서 처리 => web2.0(Ajax)
}
function detail(rank)
{
	for(let movie of movies){
		if(rank===movie.rank)
		{
			document.querySelector('#image').src='https://www.kobis.or.kr'+movie.thumbUrl
			document.querySelector('#name').innerHTML=movie.movieNm
			document.querySelector('#genre').innerHTML=movie.mgenre
			document.querySelector('#time').innerHTML=movie.showTm
			document.querySelector('#rank').innerHTML=movie.rank
			document.querySelector('#director').innerHTML=movie.director
			document.querySelector('#story').innerHTML=movie.synop
			break; //일반 for을 이용한다
		}
	}
}
</script>
</head>
<body>
  <div class="container">
   <div class="row">
    <div class="col-sm-4">
      <table class="table">
      <tbody>
        <tr>
          <td width=30% class="text-center" valign="top" rowspan="5">
            <img src="" style="width: 100%" id="image">
          </td>
          <td width=70% id="name"></td>
        </tr>
        <tr>
          <td>장르</td>
          <td id="genre"></td>
        </tr>
        <tr>
          <td>감독</td>
          <td id="director"></td>
        </tr>
        <tr>
          <td>상영시간</td>
          <td id="time"></td>
        </tr>
        <tr>
          <td width="15%">순위</td>
          <td width="50%" id="rank"></td>
        </tr>
        <tr>
          <td colspan="3" height="200" id="story"></td>
        </tr>
       </tbody>
      </table>
    </div>
    <div class="col-sm-8">
      <table class="table">
        <thead>
          <tr class="success">
            <th class="text-center">순위</th>
            <th class="text-center"></th>
            <th class="text-center">영화명</th>
            <th class="text-center">감독</th>
            <th class="text-center">장르</th>
          </tr>
        </thead>
        <tbody>
          
        </tbody>
      </table>
    </div>
   </div>
  </div>
</body>
</html>