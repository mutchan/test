/**
 * 
 * 画像選択時のプレビュー
 * 
 * @param file_input
 */
function preview(file_input) {
	if (file_input.files.length == 0) {
		return;
	}

	var file_name = file_input.files[0];
	if (!/^image\/(png|jpeg|gif)$/.test(file_name.type))
		return;

	var img = document.getElementById('preview_img');
	var fr = new FileReader();
	fr.onload = function() {
		img.src = fr.result;
	}
	fr.readAsDataURL(file_name);
}