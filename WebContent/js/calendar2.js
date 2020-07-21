"use strict";
let array = [{
    date: "2020-07-17",
    title: "GitGitGitGitGitGitGit"
  },
  {
    date: "2020-07-10",
    title: "飲み会"
  },
  {
    date: "2020-07-19",
    title: "祭り"
  },
  {
    date: "2020-07-05",
    title: "レビュー"
  },
  {
    date: "2020-08-07",
    title: "花火"
  },
  {
    date: "2020-09-27",
    title: "ライブ"
  },
  {
    date: "2020-09-28",
    title: "ライブ"
  },
  {
    date: "2020-10-12",
    title: "運動会"
  },
  {
    date: "2020-11-30",
    title: "大掃除"
  },
  {
    date: "2020-12-25",
    title: "メリクリ"
  },
]


{
  const today = new Date(); //今日の日付
  let year = today.getFullYear(); //2020
  let month = today.getMonth(); //7-1

  //次の月の分の日付
  function getCalenderTail() {
    const dates = [];
    const lastDay = new Date(year, month + 1, 0).getDay(); //曜日取得 0が日、6が土

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
    const d = new Date(year, month, 0).getDate(); //日を取得
    const n = new Date(year, month, 1).getDay(); //曜日を取得

    //dから一日ずつさかのぼってn日分の日付を取得
    for (let i = 0; i < n; i++) {
      //30
      //29,30
      //28,29,30
      dates.unshift({
        date: d - i,

        //今月かどうか
        isToday: false,

        //色を薄くするかどうか
        isDisabled: true,
      });
    }
    return dates;
  }


  function getCalenderBody() {
    const dates = []; //date:日付, day:曜日

    //翌月の0日目＝今月の末日
    //2020年「6」月の0日目を指定
    const lastDate = new Date(year, month + 1, 0).getDate();

    for (let i = 1; i <= lastDate; i++) {
      dates.push({
        date: i,
        isToday: false,
        isDisabled: false,
      });
    }

    //当月の当日のみ文字を太くする
    if (year === today.getFullYear() && month === today.getMonth()) {
      dates[today.getDate() - 1].isToday = true;
    }


    return dates;
  }


  function renderWeeks() {
    const dates = [
      //「...」はスプレッド演算子
      //これがないと多次元配列扱いされてしまうが，
      //つけると一つの配列として扱うことができる
      ...getCalenderHead(),
      ...getCalenderBody(),
      ...getCalenderTail()
    ];
    const weeks = [];
    const weeksCount = dates.length / 7; //行数 5か6か

    for (let i = 0; i < weeksCount; i++) {
      //先頭から七個分を削除しつつ取り出す
      weeks.push(dates.splice(0, 7))
    }


    //html書き出し
    weeks.forEach(week => {
      const tr = document.createElement("tr");
      week.forEach(date => {
        const td = document.createElement("td");


        //                 if (item.itemdate == "2020-07-17") {
        //                     $(this).text(`${date.date}\n${item.itemtask}`)
        // //                    	td.tecxContent += `${item.itemtask}`;
        //                 }
        /*let day = document.getElementById("title").textContent;*/
        /*        console.log("day=" + day);*/
        /*td.innerHTML = `${date.date}\n`;*/
        // 追加する要素を作成します
        var newElement = document.createElement("p"); // p要素作成
        var newContent = document.createTextNode(`${date.date}\n`); // テキストノードを作成
        newElement.appendChild(newContent); // p要素にテキストノードを追加
        newElement.setAttribute("class", "date"); // p要素にidを設定

        // ----------------------------
        // 親要素の最後の子要素を追加します
        // ----------------------------
        // 親要素（div）への参照を取得
        /*			var parentDiv = document.getElementById("parent-div");*/

        // 追加
        td.appendChild(newElement);


        array.forEach(obj => {
          let check;
          if (String(date.date).length == 1) {
            check = "0" + String(date.date);
          } else {
            check = date.date
          }
          /*          console.log(check);*/
          //if (obj.date == day.substring(0, 4) + "-" + day.substring(5, 7) + "-" + check) {
          if (obj.date == year + "-" + ("0" + (month + 1)).slice(-2) + "-" + check) {
            /*td.textContent += `${obj.title}`;*/

            // 追加する要素を作成します
            var newElement2 = document.createElement("p"); // p要素作成
            var newContent2 = document.createTextNode(`${obj.title}`); // テキストノードを作成
            newElement2.appendChild(newContent2); // p要素にテキストノードを追加
            newElement2.setAttribute("class", "yotei"); // p要素にidを設定

            // ----------------------------
            // 親要素の最後の子要素を追加します
            // ----------------------------
            // 親要素（div）への参照を取得
            /*			var parentDiv = document.getElementById("parent-div");*/

            // 追加
            td.appendChild(newElement2);

          }
        })


        if (date.isToday) {
          td.classList.add("today");
        }

        if (date.isDisabled) {
          td.classList.add("disabled");
        }

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
