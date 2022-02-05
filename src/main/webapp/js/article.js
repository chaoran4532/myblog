
function deleteComment(component,commentId){
	var container = component.parentNode.parentNode;
	$.ajax({
		type:"GET",
		url:path+"/user/commentDelete.action",
		data:{commentID:commentId},
		dataType:"json",
		success:function(data){
			console.log(data)
			if(data.delResult == "true"){//删除成功：移除删除行
				var p = container.parentNode;
				p.removeChild(container);
			}else if(data.delResult == "false"){//删除失败
				//alert("对不起，删除用户【"+obj.attr("username")+"】失败");
				alert("对不起，删除评论失败");
			}else if(data.delResult == "notexist"){
				//alert("对不起，用户【"+obj.attr("username")+"】不存在");
				alert("对不起，该评论不存在");
			}
		},
		error:function(data){
			//alert("对不起，删除失败");
			alert("对不起，删除失败");
		}
	});
}
function star_article(article_id){
	$.ajax({
		type:"GET",
		url:path+"/articleStar.action",
		data:{articleID:article_id},
		dataType:"json",
		success:function(data){
			console.log(data)
			if(data.result == "true"){
				document.getElementById("love").innerHTML= "&nbsp;"+ data.new_star+"&nbsp;";
			}else if(data.result == "false"){
				alert("不要狂点呀...");
			}
			else {
				alert("出现异常错误");
			}
		},
		error:function(data){
			alert("出现异常错误");
		}
	});
}
function star_comment(component ,commentId){
	$.ajax({
		type:"GET",
		url:path+"/commentStarOrDiss.action",
		data:{method:"star",commentID:commentId},
		dataType:"json",
		success:function(data){
			console.log(data)
			if(data.result == "true"){
				component.innerHTML= data.new_star;
			}else if(data.result == "false"){
				alert("不要狂点呀...");
			}
			else {
				alert("出现异常错误");
			}
		},
		error:function(data){
			alert("出现异常错误");
		}
	});
}
function diss_comment(component,commentId){
	$.ajax({
		type:"GET",
		url:path+"/commentStarOrDiss.action",
		data:{method:"diss",commentID:commentId},
		dataType:"json",
		success:function(data){
			console.log(data)
			if(data.result == "true"){
				component.innerHTML= data.new_diss;
			}else if(data.result == "false"){
				alert("不要狂点呀...");
			}
			else {
				alert("出现异常错误");
			}
		},
		error:function(data){
			alert("出现异常错误");
		}
	});
}

