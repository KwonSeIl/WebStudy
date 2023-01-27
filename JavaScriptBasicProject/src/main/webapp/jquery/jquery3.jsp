<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
	margin-top: 30px;
}
.row{
	width: 1200px;
	margin: 0px auto;
}
</style>
<script type="text/javascript">
//로그인, 회원가입, 회원수정, 아이디찾기, 탈퇴하기, 예약, 추천, 검색, 장바구니
let music=[{"no":1,"singer":"NewJeans","album":"NewJeans 'OMG'","title":"Ditto","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/325\/577\/83325577_1672649874616_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"qaO0Wq3Acfs"},{"no":2,"singer":"NewJeans","album":"NewJeans 'OMG'","title":"OMG","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/325\/577\/83325577_1672649874616_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"hc32lb0po9U"},{"no":3,"singer":"NewJeans","album":"NewJeans 1st EP 'New Jeans'","title":"Hype boy","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/842\/800\/82842800_1659340238924_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"11cta61wi0g"},{"no":4,"singer":"윤하 (YOUNHA)","album":"YOUNHA 6th Album Repackage 'END THEORY : Final Edition'","title":"사건의 지평선","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/614\/128\/82614128_1648619377015_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"mnpQsM-tqQU"},{"no":5,"singer":"LE SSERAFIM (르세라핌)","album":"ANTIFRAGILE","title":"ANTIFRAGILE","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/082\/184\/83082184_1665969939180_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"pyf8cbqyfPs"},{"no":6,"singer":"NewJeans","album":"NewJeans 1st EP 'New Jeans'","title":"Attention","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/842\/800\/82842800_1659340238924_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"CHp0Kaidr14"},{"no":7,"singer":"IVE (아이브)","album":"After LIKE","title":"After LIKE","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/912\/984\/82912984_1661135567760_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"F0B7HDiY-10"},{"no":8,"singer":"임영웅","album":"신사와 아가씨 OST Part.2","title":"사랑은 늘 도망가","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/322\/594\/82322594_1633671778123_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"pBEAzM2TRmE"},{"no":9,"singer":"태양","album":"VIBE","title":"VIBE (Feat. Jimin of BTS)","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/428\/324\/83428324_1673571952840_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"cXCBiF67jLM"},{"no":10,"singer":"임영웅","album":"IM HERO","title":"우리들의 블루스","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/638\/032\/82638032_1651479062721_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"YKenCC4OAyk"},{"no":11,"singer":"이영지","album":"쇼미더머니 11 Episode 3","title":"NOT SORRY (Feat. pH-1) (Prod. by Slom)","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/311\/242\/83311242_1671174043157_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"jvqbOU3gSzw"},{"no":12,"singer":"임영웅","album":"IM HERO","title":"다시 만날 수 있을까","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/638\/032\/82638032_1651479062721_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"sHqFqWDviBg"},{"no":13,"singer":"(여자)아이들","album":"I love","title":"Nxde","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/072\/254\/83072254_1665976983718_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"fCO7f0SmrDc"},{"no":14,"singer":"IVE (아이브)","album":"LOVE DIVE","title":"LOVE DIVE","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/624\/363\/82624363_1649123068092_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"Y8JFxS1HlDo"},{"no":15,"singer":"NewJeans","album":"NewJeans 1st EP 'New Jeans'","title":"Cookie","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/842\/800\/82842800_1659340238924_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"VOmIplFAGeg"},{"no":16,"singer":"임영웅","album":"내일은 미스터트롯 우승자 특전곡","title":"이제 나만 믿어요","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/081\/410\/156\/81410156_1585878705990_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"kSzraUekkNE"},{"no":17,"singer":"임영웅","album":"Polaroid","title":"London Boy","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/180\/213\/83180213_1668490985544_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"TMfvkhkALbU"},{"no":18,"singer":"지코 (ZICO)","album":"스트릿 맨 파이터(SMF) Original Vol.3 (계급미션)","title":"새삥 (Prod. by ZICO) (Feat. 호미들)","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/941\/007\/82941007_1662445894361_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"azaZt7eccnc"},{"no":19,"singer":"NCT DREAM","album":"Candy - Winter Special Mini Album","title":"Candy","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/310\/995\/83310995_1671162581876_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"zuoSn3ObMz4"},{"no":20,"singer":"임영웅","album":"Polaroid","title":"Polaroid","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/180\/213\/83180213_1668490985544_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"I82pbW2y_cc"},{"no":21,"singer":"임영웅","album":"IM HERO","title":"무지개","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/638\/032\/82638032_1651479062721_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"jP2J0qnFtV4"},{"no":22,"singer":"임영웅","album":"IM HERO","title":"아버지","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/638\/032\/82638032_1651479062721_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"r7B_9-rj9bI"},{"no":23,"singer":"Charlie Puth","album":"CHARLIE","title":"I Don't Think That I Like Her","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/484\/273\/82484273_1665067200754_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"bOVzVuB5DpY"},{"no":24,"singer":"임영웅","album":"IM HERO","title":"A bientot","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/638\/032\/82638032_1651479062721_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"zLFypmn42NE"},{"no":25,"singer":"임영웅","album":"IM HERO","title":"손이 참 곱던 그대","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/638\/032\/82638032_1651479062721_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"OpZIaI-J0uk"},{"no":26,"singer":"임영웅","album":"IM HERO","title":"사랑해 진짜","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/638\/032\/82638032_1651479062721_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"CbhiBN5wpCc"},{"no":27,"singer":"테이 (Tei)","album":"Monologue","title":"Monologue","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/983\/095\/82983095_1666323297427_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"oGsThUHfWSA"},{"no":28,"singer":"멜로망스 (MeloMance)","album":"사랑인가 봐 (사내맞선 OST 스페셜 트랙)","title":"사랑인가 봐","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/540\/759\/82540759_1645152997958_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"McidaTgrQB0"},{"no":29,"singer":"임영웅","album":"IM HERO","title":"인생찬가","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/638\/032\/82638032_1651479062721_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"7EC6RGJ8BOE"},{"no":30,"singer":"임영웅","album":"IM HERO","title":"연애편지","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/638\/032\/82638032_1651479062721_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"P2FcQvCbDWg"},{"no":31,"singer":"BLACKPINK","album":"BORN PINK","title":"Shut Down","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/912\/188\/82912188_1663300532914_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"POe9SOEKotk"},{"no":32,"singer":"임영웅","album":"IM HERO","title":"보금자리","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/638\/032\/82638032_1651479062721_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"Znpnf1HUmQ4"},{"no":33,"singer":"WSG워너비 (가야G)","album":"WSG워너비 1집","title":"그때 그 순간 그대로 (그그그)","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/814\/458\/82814458_1657270030475_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"aH-IdX8U6jc"},{"no":34,"singer":"Charlie Puth","album":"CHARLIE","title":"That's Hilarious","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/484\/273\/82484273_1665067200754_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"MPP2Q9JBvbg"},{"no":35,"singer":"(여자)아이들","album":"I NEVER DIE","title":"TOMBOY","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/586\/132\/82586132_1647227017471_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"Jh4QFaPmdss"},{"no":36,"singer":"WSG워너비 (4FIRE)","album":"WSG워너비 1집","title":"보고싶었어","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/814\/458\/82814458_1657270030475_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"B69HTHBsHCQ"},{"no":37,"singer":"BLACKPINK","album":"BORN PINK","title":"Pink Venom","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/912\/188\/82912188_1663300532914_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"gQlMMD8auMs\\u0026pp=ygUKUGluayBWZW5vbQ%3D%3D"},{"no":38,"singer":"The Kid LAROI & Justin Bieber","album":"Stay","title":"Stay","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/130\/513\/82130513_1625722837604_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"Ec7TN_11az8"},{"no":39,"singer":"Crush","album":"Rush Hour","title":"Rush Hour (Feat. j-hope of BTS)","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/998\/598\/82998598_1663838888101_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"PS0qkO5qty0"},{"no":40,"singer":"성시경","album":"별에서 온 그대 OST Part.7 (SBS 수목드라마)","title":"너의 모든 순간","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/080\/401\/547\/80401547_1392172764399_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"i2aRMXZR1k0"},{"no":41,"singer":"저스디스 (JUSTHIS) & R.Tee & 던말릭 (DON MALIK) & 허성현 (Huh) & KHAN & 맥대디 (Mckdaddy) & Los","album":"쇼미더머니 11 Episode 1","title":"마이웨이 (MY WAY) (Prod. by R.Tee)","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/243\/377\/83243377_1669883362558_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"y4Ok11V6fz8"},{"no":42,"singer":"#안녕","album":"해요 (2022)","title":"해요 (2022)","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/727\/185\/82727185_1654248208229_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"P6gV_t70KAk"},{"no":43,"singer":"김민석 (멜로망스)","album":"취중고백","title":"취중고백","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/443\/459\/82443459_1639718817315_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"FCrMKhrFH7A"},{"no":44,"singer":"10CM","album":"5.3","title":"그라데이션","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/798\/875\/82798875_1656663217923_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"kQuxJbP6s8Y"},{"no":45,"singer":"경서예지 & 전건호","album":"다정히 내 이름을 부르면","title":"다정히 내 이름을 부르면","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/051\/769\/82051769_1621328428038_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"b_6EfFZyBxY"},{"no":46,"singer":"우디 (Woody)","album":"Say I Love You (Re : WIND 4MEN Vol.04)","title":"Say I Love You","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/327\/370\/83327370_1671527785845_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"yo-cqIHBJ2U"},{"no":47,"singer":"주호","album":"내가 아니라도","title":"내가 아니라도","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/602\/752\/82602752_1648117827255_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"lAq9l8o6UXU"},{"no":48,"singer":"정국 & 방탄소년단","album":"Dreamers (Music from the FIFA World Cup Qatar 2022 Official Soundtrack)","title":"Dreamers (Music from the FIFA World Cup Qatar 2022 Official Soundtrack) (Feat. FIFA Sound)","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/083\/196\/201\/83196201_1671520555194_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"IwzkfMmNMpM"},{"no":49,"singer":"윤하 (YOUNHA)","album":"YOUNHA 6th Album 'END THEORY'","title":"오르트구름","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/393\/286\/82393286_1637039913011_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"cFgk2PMgPJ4"},{"no":50,"singer":"경서","album":"나의 X에게","title":"나의 X에게","poster":"\/\/image.genie.co.kr\/Y\/IMAGE\/IMG_ALBUM\/082\/651\/870\/82651870_1650613662156_1_140x140.JPG\/dims\/resize\/Q_80,0","key":"iAfxyHOmHrM"}]

$(function(){
	let html='';
	music.map((mu)=>{
		html+='<tr onclick="movie('+mu.no+')">'
		    +'<td class="text-center"><img src="'+mu.poster+'" width=35 height=35></td>'
		    +'<td>'+mu.title+'</td>'
		    +'<td>'+mu.singer+'</td>'
		    +'<td>'+mu.album+'</td>'
		    +"</tr>"
	})
	$('#list').html(html)
})		
function movie(no)
{
	for(let m of music) //자바스크립트의 forEach구문이라고 생각하면 된다 => for of, map, forEach (map,forEach는 return 써야함, for of는 break)
	{
		if(m.no===no)
		{
			$('#title').text(m.title) //태그와 태그 사이 값 추가
			$('#movie').attr("src","http://youtube.com/embed/"+m.key) //태그의 속성값에 값 추가
			break;
		}
	}
}
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <h1 class="text-center">뮤직 Top50</h1>
      <div class="row">
        <div class="col-sm-9">
          <table class="table table-hover">
            <thead>
              <tr>
                <th class="text-center"></th>
                <th class="text-center">곡명</th>
                <th class="text-center">가수명</th>
                <th class="text-center">앨범</th>
              </tr>
            </thead>
            <tbody id="list">
            
            </tbody>
          </table>
        </div>
        <div class="col-sm-3">
          <h3 id="title"></h3>
          <embed src="" width="300" height="250" id="movie"></embed>
        </div>
      </div>
      
    </div>
  </div>
</body>
</html>