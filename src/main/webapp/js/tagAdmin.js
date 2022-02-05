var tagObj;

function deleteTag(obj){
	$.ajax({
		type:"GET",
		url:path+"/admin/tagDelete.action",
		data:{tagID:obj.attr("tagId")},
		dataType:"json",
		success:function(data){
			console.log(data)
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//删除失败
				changeDLGContent("对不起，删除标签【"+obj.attr("title")+"】失败");
			}else if(data.delResult == "notexist"){
				changeDLGContent("对不起，标签【"+obj.attr("title")+"】不存在");
			}
		},
		error:function(data){
			//alert("对不起，删除失败");
			changeDLGContent("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){


	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteTag(tagObj);
	});

	$(".deleteTag").on("click",function(){
		tagObj = $(this);
		changeDLGContent("你确定要删除标签【"+tagObj.attr("tagName")+"】吗？");
		openYesOrNoDLG();
	});
	

});