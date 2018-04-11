<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
/********************************************
**展示数量初始化
**
*********************************************/
$(function(){
	$("#sample_select").val('${pageSize}');
});
    /***************************************
    **改变展示数量，关键字搜索，页面变成1
    **
    ***************************************/
       function selectChange()
	   {
    		var selectValue = $("#sample_select").val();
    		$("#page").val(1);
    		$("#pageSize").val(selectValue);
    		document.forms[0].submit();
	   }
	    
     /*************************************************************
      ** 分页触发页
      ** 注意 form 名称
      *************************************************************/
      function ajaxRequest(pageSize,page,name) {
    	 $("#page").val(page);
    	 $("#pageSize").val(pageSize);
    	 document.forms[0].submit();
        }


/***********************************************
 *  goPage  
 *  分页用
 *  form : 当前表单
 *  currentPage: 传入的页数
 *
 ***********************************************/
 function goPage(currentPage,pageCount)
 {
 	if(currentPage>=1)
 		{
 		if(currentPage<=pageCount)
 			{
			    	var selectValue = parseInt($("#sample_select").val());
			    	ajaxRequest(selectValue,currentPage,name);
 			}
 		}

 }
 //只能输入数字
 function keyPress() {    
     var keyCode = event.keyCode;    
     if ((keyCode >= 48 && keyCode <= 57))    
    {    
         event.returnValue = true;
     } else {    
           event.returnValue = false;    
    }    
 } 
 
//键盘操作
$(function(){
document.onkeydown = function(e){
    var ev = document.all ? window.event : e;
    var act = document.activeElement.id;
    //只有焦点在 pageNumber 下才触发 跳转事件哦 
    if(ev.keyCode==13&&act=='pageNumber') {
    	//判断是否是数字，如果是数字则action
		var pageNumber = parseInt($("#pageNumber").val());
    	if(!isNaN(pageNumber)){
				var selectValue = $("#sample_select").val();
				$("#pageSize").val(selectValue);
				$("#page").val(pageNumber);
				if(pageNumber<=${pageCount})
				{
				 document.forms[0].submit();
				}
				else
				{
					alert("超过已有页数");
				}
    		}
    	else
    	{
    		alert('only number !');
    		document.getElementById('pageNumber').focus();
    	}
     }
}
});   

</script>
 <div class="row">
			           <div class="" >
			                    	<div class="" id="sample_1_info" role="status" aria-live="polite">
			                    	     <ul class="pagination" style="visibility: visible;">
			                    	        <li>
	                                        	<select name="sample_1_length" id="sample_select" aria-controls="sample_1" class="form-control input-sm input-xsmall input-inline" onChange="selectChange();">
	                                        		<option value="1">1</option>
	                                        		<option value="20">20</option>
	                                        		<option value="30">30</option>
                                        		</select>
                                        	</li>
                                       </ul>
			                    	</div>
			           </div>
					   <div class="col-md-7 col-sm-7" >
							<div class="dataTables_paginate paging_bootstrap_full_number"   id="sample_1_paginate1" style="float:right;">
								<ul class="pagination" style="visibility: visible;">
									<c:if test="${count!=null}">
										<li>
											<a href="javascript:;" style="height:33px;">共&nbsp;${count }&nbsp;条</a>
										</li>
									</c:if>

									<li class="First" id="First"><a href="javascript:goPage(1,${pageCount});" title="First" style="height:33px;"><i class="fa fa-angle-double-left">first</i></a></li>
									<li class="prev"><a href="javascript:goPage(${page}-1,${pageCount});" title="Prev" style="height:33px;"><i class="fa fa-angle-left"></i>prev</a></li>
										<c:forEach var="i" begin="1" end="${pageCount}" varStatus="status">
											<c:choose>
												<c:when test="${status.index>page-5 && status.index<page+5}">
													<c:choose>
														<c:when test="${page==status.index}">
															<li class="active"><a href="javascript:goPage(${status.index},${pageCount});" style="height:33px;">${status.index }</a></li>
														</c:when>
														<c:otherwise>
															<li ><a href="javascript:goPage(${status.index},${pageCount});" style="height:33px;">${status.index }</a></li>
														</c:otherwise>
													</c:choose>
												</c:when>

											</c:choose>
										</c:forEach>
									<li class="next"><a href="javascript:goPage(${page+1 },${pageCount});" title="Next" style="height:33px;"><i class="fa fa-angle-right"></i>next</a></li>
									<li class="Last"><a href="javascript:goPage(${pageCount},${pageCount});" title="Next" style="height:33px;"><i class="fa fa-angle-double-right">Last</i></a></li>
									<li>&nbsp;</li>
									<li>
									 <input type="text" class="pagination-panel-input form-control input-sm input-inline input-mini" style="height:33px;" id="pageNumber" onkeypress="keyPress();" value="${page }"> 
									 </li>
								</ul>
					 			  
							</div>
					 
						</div>
	                    
</div>