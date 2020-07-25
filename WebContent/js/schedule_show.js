"use strict";



let array = [
  [{
      schedule_id: 1,
      user_id: 1,
      start_time: 480, //8:00
      dt: 60,
      title: "git"
    },
    {
      schedule_id: 2,
      user_id: 1,
      start_time: 600, //10:00
      dt: 75,
      title: "git"
    },
    {
      schedule_id: 3,
      user_id: 1,
      start_time: 14 * 60 + 15, //14:15
      dt: 15,
      title: "git"
    }
  ],
  [{
      schedule_id: 4,
      user_id: 2,
      start_time: 8.5*60,
      dt: 150,
      title: "git"
    },
    {
      schedule_id: 5,
      user_id: 2,
      start_time: 15 * 60 + 30,
      dt: 105,
      title: "git"
    }
  ],
  [{
    schedule_id: 6,
    user_id: 3,
    start_time: 13 * 60,
    dt: 180,
    title: "git"
  }],
  [{
    schedule_id: 7,
    user_id: 4,
    start_time: 10 * 60,
    dt: 180,
    title: "git"
  }],
  [{
    schedule_id: 8,
    user_id: 3,
    start_time: 9.25 * 60,
    dt: 45,
    title: "git"
  }]
]

$(function () {
  const table = document.getElementById("blackboard-table");



//ここから罫線
  let counter = 0;
  for (let i = 0; i < 11; i++) {
    const div = document.createElement("div");
    div.setAttribute("class", "schedule-line-solid");
    //div要素をtableの
    table.appendChild(div);
    const line_name = "schedule-line-solid" + counter;
    $(div).addClass(line_name);
    $("." + line_name).css("top", (i / 12 * 100) + "%");
    $("." + line_name).css("height", (1 / 12 * 100) + "%");
    counter++;
  }

  counter = 0;
  for (let i = 0; i < 12; i++) {
    const div = document.createElement("div");
    div.setAttribute("class", "schedule-line-dashed");
    //div要素をtableの
    table.appendChild(div);
    const line_name = "schedule-line-dashed1-" + counter;
    $(div).addClass(line_name);
    $("." + line_name).css("top", ((i + 0.25) / 12 * 100) + "%");
    $("." + line_name).css("height", (1 / 12 * 100) + "%");
    counter++;
  }
  counter = 0;
  for (let i = 0; i < 12; i++) {
    const div = document.createElement("div");
    div.setAttribute("class", "schedule-line-dashed");
    //div要素をtableの
    table.appendChild(div);
    const line_name = "schedule-line-dashed2-" + counter;
    $(div).addClass(line_name);
    $("." + line_name).css("top", ((i + 0.75) / 12 * 100) + "%");
    $("." + line_name).css("height", (1 / 12 * 100) + "%");
    counter++;
  }

  counter = 0;
  for (let i = 0; i < 12; i++) {
    const div = document.createElement("div");
    div.setAttribute("class", "schedule-line-dotted");
    //div要素をtableの
    table.appendChild(div);
    const line_name = "schedule-line-dotted" + counter;
    $(div).addClass(line_name);
    $("." + line_name).css("top", ((i + 0.5) / 12 * 100) + "%");
    $("." + line_name).css("height", (1 / 12 * 100) + "%");
    counter++;
  }

//時間表示
  counter = 0;
  for (let i = 0; i < 13; i++) {
    const p = document.createElement("p");
    $(p).text((i + 8) + ":00");
    p.setAttribute("class", "schedule-time");
    //div要素をtableの
    table.appendChild(p);
    const time_name = "schedule-time-" + counter;
    $(p).addClass(time_name);
    $("." + time_name).css("top", ((i - 0.17) / 12 * 100) + "%");
    $("." + time_name).css("height", (1 / 12 * 100) + "%");
    counter++;
  }


	//予定の表示！！
	  counter = 0;
  for (let i = 0; i < 5; i++) {
    const length = array[i].length;
    for (let j = 0; j < length; j++) {

      //div要素の追加
      const div = document.createElement("div");
      div.setAttribute("class", "schedule-item");
      //div要素をtableの
      table.appendChild(div);
      //それぞれの要素に固有のclass名をつける
      const class_name = "schedule" + counter;
      $(div).addClass(class_name);
      //タイトル追加
      const title = document.createElement("p");
      $(title).text(array[i][j].title);
      title.setAttribute("class", "title");
      div.appendChild(title);
      //スケジュールID追加（表示はしない）
      const schedule_id = document.createElement("p");
      $(schedule_id).text(array[i][j].schedule_id);
      schedule_id.setAttribute("class", "schedule_id");
      div.appendChild(schedule_id);
      //開始時間と終了時間の差分から高さを決定
      const height = array[i][j].dt / 720 * 100;
      $("." + class_name).css("height", height + "%");
      //開始時間からy座標を決定
      const start_point = (array[i][j].start_time - 480) / 720 * 100;
      $("." + class_name).css("top", start_point + "%");
      $("." + class_name).css("left", (19 * i + 5) + "%");

      counter++;

    }
  }


});
