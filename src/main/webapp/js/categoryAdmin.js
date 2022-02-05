var categoryObj;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteCategory(obj){
	$.ajax({
		type:"GET",
		url:path+"/admin/categoryDelete.action",
		data:{categoryID:obj.attr("categoryId")},
		dataType:"json",
		success:function(data){
			console.log(data)
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//删除失败
				changeDLGContent("对不起，删除分类【"+obj.attr("title")+"】失败");
			}else if(data.delResult == "notexist"){
				changeDLGContent("对不起，分类【"+obj.attr("title")+"】不存在");
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
		deleteCategory(categoryObj);
	});

	$(".deleteCategory").on("click",function(){
		categoryObj = $(this);
		changeDLGContent("你确定要删除分类【"+categoryObj.attr("categoryName")+"】吗？");
		openYesOrNoDLG();
	});
	

});