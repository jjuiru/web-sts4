window.addEventListener("load", function(){

	var isMobile = false;

	if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|playbook|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|ipad|iris|kindle|Android|Silk|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(navigator.userAgent) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(navigator.userAgent.substr(0,4))){
		isMobile = true;
	}


	function SlideShow_pack(_targetWrap, _intervalTime, _view_ea_D, _view_ea_T, _view_ea_M, _type){

		const $wrap = _targetWrap;
		const $wrap_el = document.querySelector($wrap);
		const $view_mask = document.querySelector($wrap +" .view_mask");
		const $inner_ul = document.querySelector($wrap +" .view_mask > ul");
		let $inner_ul_li = $inner_ul.children;
		const $btn_prev = document.querySelector($wrap +" button.prev");
		const $btn_next = document.querySelector($wrap +" button.next");
		const $pn_btns = document.querySelectorAll($wrap +" .pagination > button");

		let move_ea;
		let view_ea;
		let li_width;

		let cnt = 0;
		let _foNum;

		let si_01 = null;
		let click_Event = true;

		(function init(){
			if(_type == "fade"){
				$inner_ul.style.position = "relative";
				$inner_ul.style.width = "100%";
				
				for(var i = 0; i < $inner_ul_li.length; i++){
					$inner_ul_li[i].style.position = "absolute";
					$inner_ul_li[i].style.left = "0";
					$inner_ul_li[i].style.width = "100%";
					$inner_ul_li[i].style.height = $inner_ul_li[0].scrollHeight +"px";
					$inner_ul_li[i].style.opacity = 0;
					$inner_ul_li[i].style.visibility = "hidden";
				}
				$inner_ul.style.height = $inner_ul_li[0].scrollHeight +"px";
				$inner_ul_li[cnt].style.opacity = 1;
				$inner_ul_li[cnt].style.visibility = "visible";
			}
			else if(_type == "slide"){

				move_ea = 1;
				view_ea = (function(){
					let result;
					if(!isMobile) result = _view_ea_D;
					else {
						if(screen.width >= 768) result = _view_ea_T;
						else if(screen.width < 768) result = _view_ea_M;
					}
					return result;
				})();
				li_width = $view_mask.clientWidth / view_ea;

				for(var i = 0; i < $inner_ul_li.length; i++){
					$inner_ul_li[i].style.position = "relative";
					$inner_ul_li[i].style.width = li_width +"px";
				}
				$inner_ul.style.position = "relative";
				$inner_ul.style.width = (li_width * $inner_ul_li.length) +"px";
				for(var i = 0; i < move_ea; i++){
					$inner_ul.insertBefore($inner_ul_li[$inner_ul_li.length - 1], $inner_ul.firstChild);
				}
				$inner_ul.style.marginLeft = -(li_width * move_ea) + "px";
				$inner_ul.style.left = "0px"; 
				$inner_ul.style.transition = "left 0.3s";

			}
		})();


		if($btn_prev) $btn_prev.onclick = function(){
			count_change(-1);
		}
		if($btn_next) $btn_next.onclick = function(){
			count_change(1);
		}
		if($pn_btns.length){
			for(var i = 0; i < $pn_btns.length; i++){
				stop_si();
				$pn_btns[i].index = i;
				$pn_btns[i].addEventListener("click", function(){
					if(_type == "fade"){
						if(cnt == this.index) return false;
						count_change(0, this.index);
						pn_change(cnt);
					}
					else if(_type == "slide"){
						let cur_num = this.index;
						for(var j = 0; j < $inner_ul_li.length; j++){
							$inner_ul.appendChild(document.querySelector($wrap +" div.view_mask > ul > li[data-index = '"+ cur_num +"']"));
							cur_num = cur_num == $inner_ul_li.length - 1 ? 0 : cur_num + 1;
						}
						for(var i = 0; i < move_ea; i++){
							$inner_ul.insertBefore($inner_ul_li[$inner_ul_li.length - 1], $inner_ul.firstChild);
						}
						cnt = cur_num;
						pn_change(cnt);
						start_si();
					}
				});
			}
			function pn_change(_num){
				for(var i = 0; i < $pn_btns.length; i++){
					$pn_btns[i].classList.remove("active");
				}
				$pn_btns[_num].classList.add("active");
			}
		}

		function count_change(_dir, _idx){
			if(!click_Event) return false;
			stop_si();
			click_Event = false;
			_foNum = cnt;
			if(_dir < 0) cnt = cnt == 0 ? $inner_ul_li.length - 1 : cnt - 1;
			else if(_dir > 0) cnt = cnt == $inner_ul_li.length - 1 ? 0 : cnt + 1;
			else if(_dir == 0) cnt = _idx;
			(_type == "slide") ? move_ul(_dir) : fade_slide(cnt, _foNum);
			if($pn_btns && $pn_btns.length) pn_change(cnt);
		}

		function fadeOut(element){
			let opa_val = 1;
			let timer = setInterval(function(){
				if(!timer) click_Event = true;
				if(opa_val <= 0.1){
					opa_val = 0;
					element.style.opacity = opa_val;
					element.style.visibility = "hidden";
					clearInterval(timer);
				}
				element.style.opacity = opa_val;
				opa_val -= opa_val * 0.1
			}, 20);
		}
		function fadeIn(element){
			let opa_val = 0.1;
			element.style.visibility = "visible";
			let timer = setInterval(function(){
				if(opa_val >= 1){
					opa_val = 1;
					element.style.opacity = opa_val;
					click_Event = true;
					start_si();
					clearInterval(timer);
				}
				element.style.opacity = opa_val;
				opa_val += opa_val * 0.1
			}, 20);
		}

		function fade_slide(_num, _foNum){
			click_Event = false;
			fadeOut($inner_ul_li[_foNum]);
			fadeIn($inner_ul_li[_num]);
		}

		function move_ul(_dir){
			stop_si();
			$inner_ul.style.left = _dir < 0 ? li_width * move_ea +"px" : -(li_width) * move_ea + "px";
			$inner_ul.style.transition = "left 0.3s";
			setTimeout(function(){
				move_child(_dir);
			}, 300);
		}

		function move_child(_dir){
			_dir < 0 ? $inner_ul.insertBefore($inner_ul_li[$inner_ul_li.length - 1], $inner_ul.firstElementChild) : $inner_ul.appendChild($inner_ul.firstElementChild);
			$inner_ul.style.left = "0px";
			$inner_ul.style.transition = "none";
			click_Event = true;
			start_si();
		}

		function start_si(){
			if(si_01 != 0) clearInterval(si_01);
			si_01 = setInterval(function(){count_change(1);}, _intervalTime);
		}
		function stop_si(){
			if(si_01 != 0) clearInterval(si_01);
			si_01 = null;
		}
		start_si();

	}


	function play_video(_targetWrap){

		const $wrap = _targetWrap;
		const $iframe_wrap = document.getElementsByClassName('iframe_wrap');
		const $play_btn = document.querySelectorAll($wrap +' .iframe_wrap button');
		let $thumb = document.querySelectorAll($wrap +' .iframe_wrap img');
		let $iframe = document.querySelectorAll($wrap +' iframe');


		$play_btn.onclick = function(){
			$iframe_wrap.classList.remove('standby');
			$thumb.style.visibility = 'hidden';
			$play_btn.style.visibility = 'hidden';
			$iframe.play();
		}


		// $iframe.addEventListener('ended', function(){
		// 	$thumb.style.visibility = 'visible';
		// 	$play_btn.style.visibility = 'visible';
		// 	$iframe_wrap.classList.add('standby');
		// });


	}

	function score_star(_targetWrap){

		const $wrap = _targetWrap;
    const $label = document.querySelectorAll($wrap +' label');
		const $input = document.querySelectorAll($wrap +' input[name~="ico_star"]');
    let labelLength = $label.length;
    // opacityHover = '0.5';

		let stars = document.querySelectorAll($wrap +' span.ico_star');

		checkedRate();


		$wrap.forEach(wrap => {
				wrap.addEventListener('mouseenter', () => {
						let stars = wrap.querySelectorAll('.ico_star');

						stars.forEach((starIcon, idx) => {
								starIcon.addEventListener('mouseenter', () => {
										if (wrap.classList.contains('readonly') == false) {
												initStars(); // 기선택된 별점 무시하고 초기화
												filledRate(idx, labelLength);  // hover target만큼 별점 active

												// hover 시 active된 별점의 opacity 조정
												for (let i = 0; i < stars.length; i++) {
														if (stars[i].classList.contains('active')) {
																stars[i].style.opacity = opacityHover;
														}
												}
										}
								});

								starIcon.addEventListener('mouseleave', () => {
										if (wrap.classList.contains('readonly') == false) {
												starIcon.style.opacity = '1';
												checkedRate(); // 체크된 라디오 버튼 만큼 별점 active
										}
								});

								// rate wrap을 벗어날 때 active된 별점의 opacity = 1
								wrap.addEventListener('mouseleave', () => {
										if (wrap.classList.contains('readonly') == false) {
												starIcon.style.opacity = '1';
										}
								});

								// readonnly 일 때 비활성화
								wrap.addEventListener('click', (e) => {
										if (wrap.classList.contains('readonly')) {
												e.preventDefault();
										}
								});
						});
				});
		});

		// target보다 인덱스가 낮은 .ico_star에 .active 추가 (별점 구현)
		function filledRate(index, length) {
				if (index <= length) {
						for (let i = 0; i <= index; i++) {
								stars[i].classList.add('active');
						}
				}
		}

		// 선택된 라디오버튼 이하 인덱스는 별점 active
		function checkedRate() {
				let checkedRadio = document.querySelectorAll('.rating input[type="radio"]:checked');


				initStars();
				checkedRadio.forEach(radio => {
						let previousSiblings = prevAll(radio);

						for (let i = 0; i < previousSiblings.length; i++) {
								previousSiblings[i].querySelector('.ico_star').classList.add('active');
						}

						radio.nextElementSibling.classList.add('active');

						function prevAll() {
								let radioSiblings = [];
								let	prevSibling = radio.parentElement.previousElementSibling;

								while (prevSibling) {
										radioSiblings.push(prevSibling);
										prevSibling = prevSibling.previousElementSibling;
								}
								return radioSiblings;
						}
				});
		}

		// 별점 초기화 (0)
		function initStars() {
				for (let i = 0; i < stars.length; i++) {
						stars[i].classList.remove('active');
				}
		}

	}

	


	if(!isMobile){

		
	
	}
	else {

		if(screen.width >= 768){

			

		}
		else {


		}

	}

	let score_star_01 = score_star('.review_write');

});