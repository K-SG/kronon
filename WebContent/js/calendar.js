"use strict";

// /////////////////////////////////////////
// 表示する期間の設定
const start_month = new Date(2020,7);// 2020/08
const end_month = new Date(2023,6);// 2023/07
// //////////////////////////////////////////

// 祝日
const holidays =
 [[2020,[1,[1,13]],[2,[11,23]],[3,[20]],[4,[29]],[5,[3,4,5,6]],[6,[]],[7,[23,24]],[8,[10]],[9,[21,22]],[10,[]],[11,[3,23]],[12,[]]]
,[2021,[1,[1,11]],[2,[11,23]],[3,[20]],[4,[29]],[5,[3,4,5]],[6,[]],[7,[19]],[8,[11]],[9,[20,23]],[10,[11]],[11,[3,23]],[12,[]]]
,[2022,[1,[1,10]],[2,[11,23]],[3,[21]],[4,[29]],[5,[3,4,5]],[6,[]],[7,[18]],[8,[11]],[9,[19,23]],[10,[10]],[11,[3,23]],[12,[]]]
,[2023,[1,[1,2,9]],[2,[11,23]],[3,[21]],[4,[29]],[5,[3,4,5]],[6,[]],[7,[17]]]];


// 予定リストの取得
const json = document.getElementById("list");
// 「kronooon」を「"」に置換。全置換の場合は正規表現で//で挟みgをつける
const json_replace = (json.value).toString().replace(/krnooon/g,'"');
// jsonライブラリの関数を使って、文字列を扱いやすい形に
let array = JSON.parse(json_replace);

{

  // 表示する年月の取得
  let year = document.getElementById("year").textContent; // 2020
  let month = document.getElementById("month").textContent -1; ; // 7-1


// 指定された3年間外にアクセスできないように、端では左右ボタンを表示しない
  if((start_month.getMonth() == month)&&(start_month.getYear()+1900 == year)){
	  const left_button = document.getElementById("left");
	  left_button.style.display = 'none';
  }
  if((end_month.getMonth() == month)&&(end_month.getYear()+1900 == year)){
	  const right_button = document.getElementById("right");
	  right_button.style.display = 'none';
  }


  // 次の月の分の日付
  function getCalenderTail() {
    const dates = [];
    const lastDay = new Date(year, month + 1, 0).getDay(); // 曜日取得 0が日、6が土

    for (let i = 1; i < 7 - lastDay; i++) {
      dates.push({
        date: i,
        isToday: false,
        isDisabled: true,
      });
    }
    return dates;
  }

  function getCalenderHead() {
    const dates = [];
    const d = new Date(year, month, 0).getDate(); // 日を取得
    const n = new Date(year, month, 1).getDay(); // 曜日を取得

    // dから一日ずつさかのぼってn日分の日付を取得
    for (let i = 0; i < n; i++) {
      // 30
      // 29,30
      // 28,29,30
      dates.unshift({
        date: d - i,

        // 今月かどうか
        isToday: false,

        // 色を薄くするかどうか
        isDisabled: true,
      });
    }
    return dates;
  }


  function getCalenderBody() {
    const dates = []; // date:日付, day:曜日

    // 翌月の0日目＝今月の末日
    // 2020年「6」月の0日目を指定
    const lastDate = new Date(year, month + 1, 0).getDate();

    for (let i = 1; i <= lastDate; i++) {
      dates.push({
        date: i,
        isToday: false,
        isDisabled: false,
      });
    }
    return dates;
  }


  function renderWeeks() {
    const dates = [
      // 「...」はスプレッド演算子
      // これがないと多次元配列扱いされてしまうが，
      // つけると一つの配列として扱うことができる
      ...getCalenderHead(),
      ...getCalenderBody(),
      ...getCalenderTail()
    ];
    const weeks = [];
    const weeksCount = dates.length / 7; // 行数 5か6か

    for (let i = 0; i < weeksCount; i++) {
      // 先頭から七個分を削除しつつ取り出す
      weeks.push(dates.splice(0, 7))
    }


    // html書き出し
    weeks.forEach(week => {
      const tr = document.createElement("tr");
      week.forEach(date => {
        const td = document.createElement("td");

        // 追加する要素の作成
        var newElement = document.createElement("p"); // p要素作成
        var newContent = document.createTextNode(`${date.date}\n`); // テキストノードを作成
        newElement.appendChild(newContent); // p要素にテキストノードを追加
        newElement.setAttribute("class", "date"); // p要素にidを設定

        // 親要素tdに子要素pを追加
        td.appendChild(newElement);

        array.forEach(obj => {
          let check;
          if (String(date.date).length == 1) {
            check = "0" + String(date.date);
          } else {
            check = date.date
          }
          // 今月の日にだけ表示
          if (!date.isDisabled) {
        	  // 予定リストの日と考えている日が一致したらその予定タイトルを表示
        	  if (obj.jsonDate == year + "-" + ("0" + (month + 1)).slice(-2) + "-" + check) {

	            // 追加する要素の作成
	            var newElement2 = document.createElement("p"); // p要素作成
	            var newContent2 = document.createTextNode(`${obj.title}`); // テキストノードを作成
	            newElement2.appendChild(newContent2); // p要素にテキストノードを追加
	            newElement2.setAttribute("class", "yotei"); // p要素にclassを設定

	            // 追加
	            td.appendChild(newElement2);
        	  }
          }
        })

        // 今月でない日の部分は文字を薄くする
        if (date.isDisabled) {
          td.classList.add("disabled");
        }else{

	        // 祝日に色付け
	        for(let i=0;i<holidays[year-2020][month+1][1].length;i++){
	        	if(holidays[year-2020][month+1][1][i] === date.date){
	        		td.classList.add("holiday");
	        	}
	        }
        }
        // 追加
        tr.appendChild(td);

      });
      document.querySelector("tbody").appendChild(tr);
    });

  }

  function createCalender() {

    renderWeeks();

  }

  createCalender();

}


// くろのんメッセージ
// 時間帯に応じて変わるよ //夜はランダムだよ
const random = Math.random();

let nt = new Date();
let hr = nt.getHours();
const message = document.getElementById("kronon-message");
if((hr>=4)&&(hr<11)){
	if(random < 0.3){message.innerHTML="<p>おはよう☀<br>お仕事がんばってね。</p>";}
	else if(random < 0.6){message.innerHTML="<p>おはよう☀<br>今日もお疲れ様！</p>";}
	else{message.innerHTML="<p>おはよう☀<br>今日はどんな予定があるの？</p>";}
}else if((hr>=11)&&(hr<18)){
	if(random < 0.3){message.innerHTML="<p>こんにちは★<br>お仕事がんばってね。</p>";}
	else if(random < 0.6){message.innerHTML="<p>こんにちは★<br>今日もお疲れ様！</p>";}
	else{message.innerHTML="<p>こんにちは★<br>今日はどんな予定があるの？</p>";}
}else{
	if(random < 0.3){message.innerHTML="<p>こんばんは。<br>まだまだ働こうね★</p>";}
	else if(random < 0.6){message.innerHTML="<p>こんばんは。<br>今日もお疲れ様！</p>";}
	else{message.innerHTML="<p>こんばんは。<br>まだ帰らないの？</p>";}
}

