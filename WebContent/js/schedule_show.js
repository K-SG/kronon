"use strict";

///////////////////////////////////////////
//表示する期間の設定
const start_day = "2020-08-01";
const end_day = "2023-07-31";
///////////////////////////////////////////


//スケジュールリストの取得
let json = document.getElementById("list");
let json_replace = (json.value).toString().replace(/krnooon/g,'"');
let array_json = JSON.parse(json_replace);

//名前リストの取得
let json2 = document.getElementById("name-list");
let json_replace2 = (json2.value).toString().replace(/krnooon/g,'"');
let array_json_name = JSON.parse(json_replace2);

//表示人数の取得
let user_num = array_json_name.length;

//スケジュールの幅の指定
let margin_width = 100 / (4*user_num+1);
let schedule_width = margin_width * 3;

//指定された3年間外にアクセスできないように、端では左右ボタンを表示しない
const date = document.getElementById("scheduleDate").value;
if(start_day === date){
	const left_button = document.getElementById("left");
	left_button.style.display = 'none';
}
if(end_day === date){
	const right_button = document.getElementById("right");
	right_button.style.display = 'none';
}

$(function () {
	//枠の取得
	const table = document.getElementById("blackboard-table");

///////ここから罫線//////////////////////////////////////////////////////////////////////

	//●●:00 の線
	let counter = 0;
	for (let i = 0; i < 12; i++) {
		const div = document.createElement("div");
		div.setAttribute("class", "schedule-line-solid");
		table.appendChild(div);
		const line_name = "schedule-line-solid" + counter;
		$(div).addClass(line_name);
		$("." + line_name).css("top", (i / 12 * 100) + "%");
		$("." + line_name).css("height", (1 / 12 * 100) + "%");
		counter++;
	}

	//●●:15 の線
	counter = 0;
	for (let i = 0; i < 12; i++) {
		const div = document.createElement("div");
		div.setAttribute("class", "schedule-line-dashed");
		table.appendChild(div);
		const line_name = "schedule-line-dashed1-" + counter;
		$(div).addClass(line_name);
		$("." + line_name).css("top", ((i + 0.25) / 12 * 100) + "%");
		$("." + line_name).css("height", (1 / 12 * 100) + "%");
		counter++;
	}
	//●●:45 の線
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

	//●●:30 の線
	counter = 0;
	for (let i = 0; i < 12; i++) {
		const div = document.createElement("div");
		div.setAttribute("class", "schedule-line-dotted");
		table.appendChild(div);
		const line_name = "schedule-line-dotted" + counter;
		$(div).addClass(line_name);
		$("." + line_name).css("top", ((i + 0.5) / 12 * 100) + "%");
		$("." + line_name).css("height", (1 / 12 * 100) + "%");
		counter++;
	}
/////////罫線ここまで/////////////////////////////////////////////////////////////////////////

	//時刻表示
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

	//名前表示
	const name_container = document.getElementById("name-container");
	counter = 0;
	for (let i = 0; i < user_num; i++) {
		//div要素の追加
		const div = document.createElement("div");
		$(div).addClass("username");
		name_container.appendChild(div);
		//それぞれの要素に固有のclass名をつける
		const class_name = "name" + counter;
		$(div).addClass(class_name);
		//名前を追加
		$(div).text(array_json_name[i]);
		//座標指定
		$("."+class_name).css("left",((margin_width+schedule_width) * i + margin_width) + "%");
		//幅指定
		$("."+class_name).css("width",(schedule_width) + "%");

		counter++;
	}


	//予定の表示！！
	counter = 0;
	for (let i = 0; i < user_num; i++) {
		const length = array_json[i].length;
		for (let j = 0; j < length; j++) {

			//div要素の追加
			const div = document.createElement("div");
			$(div).addClass("schedule-item");
			//div要素をtableの子要素に
			table.appendChild(div);
			//それぞれの要素に固有のclass名をつける
			const class_name = "schedule" + counter;
			$(div).addClass(class_name);
			//タイトル追加
			const title = document.createElement("p");
			$(title).text(array_json[i][j].title);
			$(title).addClass("title");
			div.appendChild(title);
			//スケジュールID追加（表示はしない）
			const schedule_id = document.createElement("p");
			$(schedule_id).text(array_json[i][j].scheduleId);
			schedule_id.setAttribute("class", "schedule_id");
			div.appendChild(schedule_id);


			//見積時間を分換算
			let estimateTimeArray = array_json[i][j].startTime.split(/\D/g);//\Dは文字の正規表現
			let estimateHour = Number(estimateTimeArray[0]);
			let estimateMinute = Number(estimateTimeArray[1]);
			let startTimeMinute = 60*estimateHour + estimateMinute;

			estimateTimeArray = array_json[i][j].endTime.split(/\D/g);//\Dは文字の正規表現
			estimateHour = Number(estimateTimeArray[0]);
			estimateMinute = Number(estimateTimeArray[1]);
			let endTimeMinute = 60*estimateHour + estimateMinute;

			let dt = endTimeMinute - startTimeMinute;


			//開始時間と終了時間の差分から高さを決定
			const height = dt / 720 * 100;
			$("." + class_name).css("height", height + "%");
			//タイトルが切れないようにタイトルの高さを設定//100vwが100%に対応//行間1.2vw
			$(title).css("height",Math.floor(height/1.2-0.5)*1.2+ "vw");
			//開始時間からy座標を決定
			const start_point = (startTimeMinute - 480) / 720 * 100;
			$("." + class_name).css("top", start_point + "%");
			$("." + class_name).css("left", ((margin_width+schedule_width) * i + margin_width) + "%");
			$("."+class_name).css("width",(schedule_width) + "%");

			//場所に応じて色付け
			const place = array_json[i][j].place;
			if(place === "0"){
				//div.setAttribute("class", "office");
				$("." + class_name).css("background-color","#FFD8D6");
				$("." + class_name).css("border-color","#FFA8A3");//#FFC0BD
			}else if(place === "1"){
				//div.setAttribute("class", "home");
				$("." + class_name).css("background-color","#FFF08D");
				$("." + class_name).css("border-color","#FFE959");//#FFEC73
			}else if(place === "2"){
				//div.setAttribute("class", "away");
				$("." + class_name).css("background-color","#E1FBFF");
				$("." + class_name).css("border-color","#ADF4FF");//#C7F8FF
			}

			counter++;

		}
	}


});
