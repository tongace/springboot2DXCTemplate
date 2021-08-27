//<![CDATA[
'use strict'
let HOME = (function ($) {
	return {

	}
}(jQuery));

$(document).ready(function () {
	'use strict'
	let a = '[[#{welcome.message}]]';

	$('#birthDateCal').calendar({
		type: 'date',
		formatter: {
			date: function (date, settting) {
				return DXCUtils.formatDate(date, 'DD/MM/YYYY');
			}
		},
		parser: {
			date: function (text, settings) {
				return DXCUtils.parseDate(text, 'DD/MM/YYYY');
			}
		},
		maxDate: moment().startOf('day').toDate(),
		today: true,
		monthFirst: false
	});
	$('#birthDateCal').calendar('set date', moment().toDate(), true, false);

	const blobToData = (blob) => {
		return new Promise((resolve) => {
			const reader = new FileReader()
			reader.onloadend = () => resolve(reader.result)
			reader.readAsDataURL(blob)
		})
	};

	$('#uploadFile').on('change', async (e) => {
		const file = e.target.files[0];
		let data = await blobToData(file);
		$('#uploadPic').attr('src', data);
		$('#uploadLink').attr('href', data);
		$('#uploadLink').attr('download', file.name);
		$('#imageModal').modal({
			selector: {
				close: '#modalUploadOK'
			},
			duration: 0
		}).modal('show');;
	});
	// $('#uploadDelete').on('click', _.debounce(async (e) => {
	// 	$('#uploadPic').removeAttr('src');
	// 	$('#uploadLink').removeAttr('href');
	// 	$('#uploadLink').removeAttr('download');
	// 	$('#uploadCard').hide();
	// }, 300, true));
	$('#upload').on('click', _.debounce(async (e) => {
		const formValues = $('#mainForm').form('get values');
		console.log(formValues);
		// const file = $('#uploadFile').prop("files")[0];
		// let formData = new FormData();
		// let userModel = {};
		// userModel.id = "3333333333333";
		// userModel.firstName = "Test Name";
		// userModel.lastName = "Test Last Name";
		// let userBlob = new Blob([JSON.stringify(userModel)], {
		// 	type: "application/json"
		// });
		// formData.append("userModel", userBlob);
		// formData.append("userPic", file, file.name);
		// console.log(formData.keys());
		// let response = await DXCUtils.callAPIWithUploadFile("/demo/home/upload", "POST", formData);
		// console.log(response);
	}, 300, true));
});
//]]>