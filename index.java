<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--<script src="/msservoapp/js/plugins/sweetalert/sweetalert.min.js"></script>
<link href="/msservoapp/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="/msservoapp/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<link href="/msservoapp/css/plugins/select2/select2.min.css" rel="stylesheet">-->

<style type="text/css">
/*    .select2-container--default .select2-selection--single {
        border-radius: 0px !important;
        height: 34px !important;
        border-color: #e5e6e7 !important;
    }*/
    .no-warp {
        white-space:nowrap;
    }
    .btnInfo{
        padding: 3px;
        line-height: 10px;
        margin: 0px;
    }
</style>
<!--<div class="panel-heading"></div>-->
<!--<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-2" style="width: 100px">
        <div class="img-preview img-preview-sm" style="height:100px; width: 100px; padding: 0px; margin: 0px;text-align: center">
            <img id="imgLogo" src="/msservoapp/web/data/Logo/Pride_logo.png" width="100px" align="center" alt="Pride_logo">
        </div>
    </div>
    <div class="col-lg-10">
        <h2>${module.caption}</h2>
        <ol class="breadcrumb">
            <strong style="font-weight: bold;">${urlWeb}</strong>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>-->
<div class="wrapper wrapper-content animated fadeInRight" id="pnlEmployeeGridAll">
    <div class="row">
        <div class="col-sm-12" style="font-family: Trebuchet MS">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-6">
                            <input type="hidden" id="txtUserId" name="creatorid" value="${user.AdminId}">
                            <input type="hidden" id="txtUserBranch" name="creatorbranch" value="${user.CodeCabangAdmin}">
                            <input type="hidden" id="txtnewTime" name="newTime" value="${newTime}">
                            <c:choose>
                                <c:when test="${moduleId == 'CNHCHC26'}">
                                    SELECTION STAGE
                                </c:when>
                                <c:otherwise>
                                    SALARY NEGOTIATION
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-md-6" style="text-align: right">
                            <input type="text" id="cekUserType" style="color: black; width: 10px" value="${user.AdminType}" disable class="hidden">
                            <input type="text" id="cekUserId" style="color: black; width: 35px" value="${user.AdminId}" disable class="hidden">
                            <input type="text" id="grid" style="color: black; width: 35px" value="${grid}" disable class="hidden">
                            <input type="text" id="moduleIdEss" style="color: black; width: 35px" value="${moduleIdEss}" disable class="hidden">
                            <input type="text" id="moduleId" style="color: black; width: 35px" value="${moduleId}" disable class="hidden">
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <input type="text" id="statusTab" style="color: black; width: 35px" value="${statusTab}" disable class="hidden">
                    <!--<div class="ibox float-e-margins">-->
                        <div class="ibox float-e-margins hidden" style="margin-bottom: 0; font-family: Trebuchet MS" id="pnlLeaveHeaderFilter">
                            <div class="ibox-title">
                                <div class="row">
                                    <div class="col-md-6"><h5>Search</h5></div>
                                    <div class="col-md-6"></div>
                                </div>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content" style="display: block;padding-left: 15px;">
                                <blockquote style="padding-left: 0; font-size: 12px">
                                    <form id="formImportFilter" method="post">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="col-md-12 form-group" id="divCreatorLeave">
                                                    <label class="col-md-3">Pembuat</label>
                                                    <div class="col-md-8 input-group">
                                                        <select class="select-findStdField" name="ddlcreator" style="width: 300px;" id="ddlCreatorLeave">
                                                            <option value="ALL">ALL</option>
                                                            <c:forEach var="creator" items="${creatorList}" >
                                                                <option value="${creator.Employee_id}"
                                                                    <c:if test="${viewCreator==creator.Employee_id}">selected="true"</c:if>>${creator.Employee_id2} - ${creator.Employee_name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                // <div class="col-md-12 form-group">
                                                //     <label class="col-md-3" id="ddltype">Status Dokumen</label>
                                                //     <div class="col-md-8 input-group">
                                                //         <select class="select-findStdField" style="width: 300px;" name="signtobetype" id="ddlDocStatus">
                                                //             <option value="">ALL</option>
                                                //             <c:forEach var="docstatus" items="${listDocStatus}">
                                                //                 <option value="${docstatus.applicationMstStandardFieldDetailPK.stdCode}"
                                                //                         <c:if test="${docStatus==docstatus.applicationMstStandardFieldDetailPK.stdCode}">selected="true"</c:if>>${docstatus.stdName}</option>
                                                //             </c:forEach>
                                                //         </select>
                                                //     </div>
                                                // </div>
                                                <div class="col-md-12 form-group" style="margin-bottom: 0;">
                                                    <label class="col-md-3 control-label"></label>
                                                    <div class="col-md-8 input-group">
                                                        <div class="btn-group">
                                                            <span class="btn btn-primary" id="pnlViewLeave"><i class="fa fa-search"></i> </span>
                                                            <button class="btn btn-primary btn-outline" type="button" id="btnViewLeave">Tampilkan</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6"></div>
                                        </div>
                                    </form>
                                </blockquote>
                            </div>
                        </div><br/>
                        <form class="form-horizontal" method="POST" class="ibox-content" id="FormGridLeave">
                            <div class="row panel-heading lazur-bg" style="margin-left: 0;margin-right: 0;padding-left: 0;padding-right: 0;" id="gridRecruitment_pnlHeading">
                                <div class="col-md-4 pull-right">
                                    <div class="input-group col-sm-12" style=" color: #676a6c">
                                        <input type="text" placeholder="Search" id="gridRecruitment_filterText" value="${wherecond}" name="gridRecruitment_filtertext" class="form-control">
                                        <span class="input-group-btn">
                                            <button id="btnGridLeave_filterText" type="button" class="btn btn-success blue-skin searchLeave" style="border-color: #fff;">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <select class="form-control blue-bg blue-skin"
                                            name="gridRecruitment_navrecordstodispaly"
                                            id="gridRecruitment_navRecordsToDispaly">
                                        <option <c:if test="${pagesize==10}">selected="true"</c:if>>10</option>
                                        <option <c:if test="${pagesize==25}">selected="true"</c:if>>25</option>
                                        <option <c:if test="${pagesize==50}">selected="true"</c:if>>50</option>
                                        </select>
                                    </div>
                                </div>
                                <div style="overflow-x:auto;">
                                    <table class="table table-bordered table-hover" id="gridRecruitment" style="margin-bottom: 0px">
                                        <thead>
                                            <tr>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle">NO</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle">VACANCY |<br/>SELECTION</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle">RECRUITMENT TYPE</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle" class="">REQUEST TYPE</th>
                                                <th colspan="1" style="text-align: center; vertical-align: middle" class="">WORKING TYPE | <br/>CATEGORY</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle" class="">LEVEL</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle" class="">POSITION</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle" class="">PLACEMENT <br/>(BRANCH - LOCATION)</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle" class="">FULFILLMENT TARGET</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle" class="">PUBLISH VACANCY</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle" class="">QUOTA</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle" class="">APPLICANT</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle" class="">PASS</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle" class="<c:if test="${moduleId == 'CNHCHC26'}">hidden</c:if>">DOCUMENT STATUS OFFERING</th>
                                                <th rowspan="2" style="text-align: center; vertical-align: middle">SELECTION</th>
                                            </tr>
<!--                                            <tr>
                                                <th style="text-align: center; vertical-align: middle">TYPE | <br/>CATEGORY</th>
                                            </tr>-->
                                        </thead>
                                        <tbody>
                                        <c:forEach var="vacancy" items="${vacancyList}">
                                            <tr>
                                                <td>${vacancy.ID}</td>
                                                <td>${vacancy.Novacancy}<br/>${vacancy.Norequest_req}</td>
                                                <td>${vacancy.Recruitment_type_name}</td>
                                                <td>${vacancy.Request_type_name}</td>
                                                <td>
                                                    <small>${vacancy.Working_type_name} | </small><br/>
                                                    ${vacancy.Working_category_name}
                                                </td>
                                                <td>${vacancy.job_type}</td> <!--${vacancy.Job_type_name}-->
                                                <td>${vacancy.Job_name}</td>
                                                <td>${vacancy.BranchLoc}</td>
                                                <td>${vacancy.dateFromToFull}<br/>
                                                    <small>(${vacancy.dayTotalFull} Day) </small>
                                                </td>
                                                <td>${vacancy.dateFromTo}<br/>
                                                    <small>(${vacancy.dayTotal} Day) </small>
                                                </td>
<!--                                                <td>
                                                    ${vacancy.dateFromTo}<br/>
                                                    <small>(${vacancy.dayTotal} Day) </small>
                                                    <c:choose>
                                                        <c:when test="${vacancy.cekAktif == 0 or vacancy.Closing == 1 }">
                                                            <label style="margin: 0px;line-height: 14px;" class="btn-danger btn-xs"> <small>${vacancy.cekAktif_name}</small></label>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <label style="margin: 0px;line-height: 14px;" class="btn-primary btn-xs"> <small>${vacancy.cekAktif_name}</small></label>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>-->
                                                <td>${vacancy.Kuota_total}</td>
                                                <td>${vacancy.countApplicant}</td>
                                                <td>${vacancy.countLulus}</td>
                                                <td class="hidden">
                                                    <div class="<c:if test="${vacancy.Closing_selection == 0 || vacancy.Closing_selection == null}">hidden</c:if>">
                                                        <input <c:if test="${vacancy.Closing_selection == 1}">checked="true"</c:if> type="checkbox" class="checkbox" disabled="false" title="Closed"><br/> <small>${vacancy.Closing_selection_date}</small>
                                                    </div>
                                                    <c:if test="${moduleId == 'CNHCHC26'}">
                                                        <button type="button" id="btnClosing" class="btn btn-danger btnClosing btn-xs <c:if test="${vacancy.Closing_selection == 1}">hidden</c:if>"><i class="fa fa-close" title="Closing"></i> Closing</button>
                                                    </c:if>
                                                </td>
                                                <td class="no-warp <c:if test="${moduleId == 'CNHCHC26'}">hidden</c:if>" style="text-align: center">
                                                    <a class="btn btn-link href" style="padding: 0;margin: 0;" href="#" onclick="btnEditDetailSelection('${vacancy.noselection}','${vacancy.Novacancy}')">
                                                        <c:if test="${vacancy.document_status==null}">
                                                            <button type="button" class="btn btn-default btn-xs">Waiting for Approval</button>
                                                        </c:if>
                                                        <c:if test="${vacancy.document_status==1}">
                                                            <button type="button" class="btn btn-warning btn-xs">Processed</button>
                                                        </c:if>
                                                       <c:if test="${vacancy.document_status==2}">
                                                            <button type="button" class="btn btn-danger btn-xs">Approved</button>
                                                        </c:if>
                                                        <c:if test="${vacancy.document_status==3}">
                                                            <button type="button" class="btn btn-danger btn-xs">Rejected</button>
                                                        </c:if>
                                                         <c:if test="${vacancy.document_status==4}">
                                                            <button type="button" class="btn btn-warning btn-xs">Revision</button>
                                                        </c:if>
                                                    </a>
                                                </td>
                                                <td>
                                                    <input id="jobid" value="${vacancy.Job_id}" data-jobid="${vacancy.Job_id}" class="hidden">
                                                    <a class="btn btn-link href btnTSelection2 <c:if test="${vacancy.Closing_selection == 1}">hidden</c:if>" style="padding: 0;margin: 0;" href="#" data-novacancy="${vacancy.novacancy}" onclick="btnEditDetailSelection('${vacancy.noselection}','${vacancy.Novacancy}')">
                                                        <button type="button" class="btn btn-warning btn-xs"><i class="fa fa-sign-out" title="Stages"></i> Stages</button>
                                                    </a>
                                                    <a class="btn btn-link href btnTSelection2 <c:if test="${vacancy.Closing_selection == 0 || vacancy.Closing_selection == null}">hidden</c:if>" style="padding: 0;margin: 0;" href="#" data-novacancy="${vacancy.novacancy}" onclick="btnEditDetailSelection('${vacancy.noselection}','${vacancy.Novacancy}')">
                                                        <button type="button" class="btn btn-success btn-xs"><i class="fa fa-check" title="Done"></i> Done</button>
                                                    </a>
                                                </td>
                                            </tr>

                                        </c:forEach>
                                    </tbody>
<!--                                    <tfoot>
                                    <td colspan="11"></td>
                                    </tfoot>-->
                                </table>
                            </div>
                            <div class="panel-footer lazur-bg m-b-n-sm" style="line-height: initial;" id="gridRecruitment_pnlFooter">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="pull-right">
                                            Page <label id="gridRecruitment_pageCurrent">${currentpage}</label> from <label id="gridRecruitment_pageTotal">${totalpage}</label><br />
                                            From <label id="gridRecruitment_recordTotal" name="gridRecruitment_recordtotal">${totalrecords}</label>  Total Data
                                            <input type="hidden" name="gridRecruitment_pagetotal" value="${totalpage}">
                                        </div>
                                        <div class="form-inline">
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <div class="btn-group m-b-n-sm">
                                                        <button type="button" <c:if test="${first==true}">disabled</c:if>  class="btn btn-success blue-skin btn-navLeave" data-nav="first" id="gridRecruitment_navPageFirst"><i class="fa fa-step-backward"></i></button>
                                                        <button type="button" <c:if test="${first==true&&last==false}">disabled</c:if> class="btn btn-success blue-skin btn-navLeave" data-nav="prev" id="gridRecruitment_navPagePrev"><i class="fa fa-backward"></i> </button>
                                                        <button type="button" <c:if test="${last==true&&first==false}">disabled</c:if> class="btn btn-success blue-skin btn-navLeave" data-nav="next" id="gridRecruitment_navPageNext"><i class="fa fa-forward"></i> </button>
                                                        <button type="button" <c:if test="${last==true}">disabled</c:if> class="btn btn-success blue-skin btn-navLeave" data-nav="last" id="gridRecruitment_navPageLast"><i class="fa fa-step-forward"></i> </button>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="input-group col-sm-6" style=" color: #676a6c">
                                                            <input type="text" id="gridRecruitment_navPageJumpTo" name="gridRecruitment_navpagejumpto" class="form-control" value="${currentpage}">
                                                        <span class="input-group-btn m-b-n-sm">
                                                            <button type="button" class="btn btn-success btn-bitbucket blue-skin btn-navLeave" data-nav="jump">
                                                                <i class="fa fa-share-square-o"></i>
                                                            </button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    <!--</div>-->
                </div>
            </div>
        </div>
    </div>
</div>
<!--<script src="/msservoapp/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="/msservoapp/js/plugins/select2/select2.full.min.js"></script>   -->
<!-- Data picker -->
<!--<script src="/msservoapp/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="/msservoapp/js/plugins/dataTables/pdfmake.min.js" type="text/javascript"></script>
<script src="/msservoapp/js/plugins/dataTables/vfs_fonts.js" type="text/javascript"></script>
<script src="/msservoapp/js/plugins/imgResize/jquery.ae.image.resize.min.js" type="text/javascript"></script>                                        -->
<script type="text/javascript">

    var checkNull =  Number(0);
    $(".chkNull:checked").each(function(){
        checkNull+=1;
    });

    $('.collapse-link').click(function () {
        var ibox = $(this).closest('div.ibox');
        var button = $(this).find('i');
        var content = ibox.find('div.ibox-content');
        content.slideToggle(200);
        button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
        ibox.toggleClass('').toggleClass('border-bottom');
        setTimeout(function () {
            ibox.resize();
            ibox.find('[id^=map-]').resize();
        }, 50);
    });
    $(".select-findStdField").select2();
    if($('#cekUserId').val() !== $('#cekUserId').val()){
        if($('#cekUserType').val() === "1" ){
            $('#divNewLeave').show();
            $('#divCreatorLeave').hide();
        }
        else if($('#cekUserType').val() === "2"){
            $('#divNewLeave').show();
            $('#divCreatorLeave').show();
        }
        else{
            $('#divNewLeave').hide();
            $('#divCreatorLeave').hide();
        }
    }else{
        if($('#cekUserType').val() === "1" ){
            $('#divNewLeave').show();
            $('#divCreatorLeave').hide();
        }
        else if($('#cekUserType').val() === "2"){
            $('#divNewLeave').show();
            $('#divCreatorLeave').hide();
        }
        else if($('#cekUserType').val() === "3" || $('#cekUserType').val() === "4"){
            $('#divNewLeave').show();
            $('#divCreatorLeave').show();
        }else{
            $('#divNewLeave').show();
            $('#divCreatorLeave').hide();
        }
    }


//            $('#divNewLeave').show();
//            $('#divCreatorLeave').hide();

//    if(($('#cekUserId').val() === $('#cekUserId').val())){
//        $("#divCreatorLeave").select2("val", $('#cekUserId').val());
//    }

//    if(($('#cekUserId').val() == $('#cekPositionby').val()) || ($('#cekUserType').val() === "3" || $('#cekUserType').val() === "4")){
//        $("#ddlDocStatus").select2("val", "1");
//    }

        function btnEditHeaderDetailVacancy(noselection,Novacancy) {
        loadHome('/msservoapp/web/recruitment/opendata-recruitmentrequestsalary/' + noselection + "?"
                +"&novacancy="+Novacancy+"&creatorId="+$("#cekUserId").val()+"&adminId="+$("#cekUserId").val()+"&grid="+$("#grid").val()+'&ranNum=${ranNum}&u=${u}&moduleId=${moduleId}&moduleIdEss=${moduleIdEss}'+"&userId="+$("#txtUserId").val()+"&statusTab="+${statusTab});
    }

    function btnBack() {
        loadHome('/msservoapp/web/recruitment/vacancy-grid'+'?ranNum=${ranNum}&u=${u}&moduleId=${moduleId}&moduleIdEss=${moduleIdEss}'+"&userId="+$("#txtUserId").val()+"&statusTab="+${statusTab});
    }
    function btnDeleteHeaderDetail(Novacancy) {
        swal({
            title: "Hapus Data?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes!",
            closeOnConfirm: false
        },
                function (isConfirm) {
                    if (isConfirm)
                    {
                        swal("Dihapus!", "", "success");
                        loadHome('/msservoapp/web/recruitment/delete-vacancyheaderdetail/' + Novacancy+"?creatorId="+$("#cekUserId").val()+"&adminId="+$("#cekUserId").val()+"&grid="+$("#grid").val()+'&ranNum=${ranNum}&u=${u}&moduleId=${moduleId}&moduleIdEss=${moduleIdEss}'+"&userId="+$("#txtUserId").val()+"&statusTab="+${statusTab});
                    } else {

                    }
                });
    }
    function btnDeleteStatusHeaderDetail(Novacancy) {
    swal({
        title: "Batalkan Transaksi?",
        text: "Berikan Alasan Anda : ",
        type: "input",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: "slide-from-top",
        inputPlaceholder: "Write something"
      },
      function(inputValue){
        if (inputValue === false) return false;

        if (inputValue === "") {
          swal.showInputError("Alasan tidak boleh kosong!");
          return false;
        }
        if (inputValue)
        {
            swal("Dibatalkan!", "", "success");
            loadHome('/msservoapp/web/recruitment/deleted-vacancy?Novacancy=' + Novacancy+"&creatorId="+$("#cekUserId").val()+ "&creatorbox=" + $('#htxtBoxIDCreator').val()+ "&commentapprover=" + encodeURIComponent(inputValue)+"&adminId="+$("#cekUserId").val()+"&grid="+$("#grid").val()+'&ranNum=${ranNum}&u=${u}&moduleId=${moduleId}&moduleIdEss=${moduleIdEss}'+"&userId="+$("#txtUserId").val()+"&statusTab="+${statusTab});
        }
        swal("Dibatalkan!", "Alasan: " + inputValue, "success");
      });
    }
    $('.btnTSelection').click(function () {
        var Novacancy = $(this).parents('tr').find('.btnTSelection').data('Novacancy');
        var jobid = $('.btnTSelection').parents('tr').find('input').data('jobid');
        loadHome('/msservoapp/web/recruitment/transaction-selectionapplicant?Novacancy='+Novacancy+"&jobId="+jobid+"&creatorId="+$("#cekUserId").val()+"&adminId="+$("#cekUserId").val()+"&grid="+$("#grid").val()+'&ranNum=${ranNum}&u=${u}&moduleId=${moduleId}&moduleIdEss=${moduleIdEss}'+"&userId="+$("#txtUserId").val()+"&statusTab="+${statusTab});

        return false;
    });

     function btnEditDetailSelection(noselection,novacancy) {
        loadHome('/msservoapp/web/recruitment/transaction-selectionapplicant?novacancy='+novacancy+"&creatorId="+$("#cekUserId").val()+"&adminId="+$("#cekUserId").val()+"&grid="+$("#grid").val()+'&ranNum=${ranNum}&u=${u}&moduleId=${moduleId}&moduleIdEss=${moduleIdEss}'+"&userId="+$("#txtUserId").val()+"&statusTab="+${statusTab});
    }

    $('.btnClosing').click(function () {
        var Novacancy = $(this).parents('tr').find('.btnTSelection').data('Novacancy');

        swal({
            title: "Closing Data?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes!",
            closeOnConfirm: false
        },
        function (isConfirm) {
            if (isConfirm)
            {
            swal("Closing!", "", "success");
                loadHome('/msservoapp/web/recruitment/closing-selection'+"?"
                        +"Novacancy="+Novacancy
                        +"&closingtable=1"
                        +"&creatorId="+$("#cekUserId").val()
                        +"&adminId="+$("#cekUserId").val()
                        +"&creatorIdView=" + $("#cekUserId").val()
                        +'&ranNum=${ranNum}&u=${u}&moduleId=${moduleId}'+"&userId="+$("#txtUserId").val()+"&statusTab="+${statusTab}
                );
            }
        });
        return false;
    });
//    function btnEditHeaderDetail(Novacancy) {
//        loadHome('/msservoapp/web/recruitment/opendata-vacancydetail/' + Novacancy + '/1'
//            + "?creatorId=" + $("#cekUserId").val() + "&adminId=" + $("#cekUserId").val() + "&grid=" + $("#grid").val());
//    }

    //-------------------
    function postBackLeave(){
        loadHome('/msservoapp/web/recruitment/transaction-selectionvacancy?'+ $("#FormGridLeave").serialize()
                + "&viewCreator=" + $("#ddlCreatorLeave").val()
                + "&docStatus=" + $("#ddlDocStatus").val()
                +"&creatorId="+$("#cekUserId").val()+"&adminId="+$("#cekUserId").val()+'&ranNum=${ranNum}&u=${u}&moduleId=${moduleId}&moduleIdEss=${moduleIdEss}&vacancyToday='+"&userId="+$("#txtUserId").val()+"&statusTab="+${statusTab});
    }
    function reloadGridLeave(objControl){
        loadHome('/msservoapp/web/recruitment/transaction-selectionvacancy?nav='+$(objControl).data("nav")+ "&" + $("#FormGridLeave").serialize()
                + "&viewCreator=" + $("#ddlCreatorLeave").val()
                + "&docStatus=" + $("#ddlDocStatus").val()
                +"&creatorId="+$("#cekUserId").val()+"&adminId="+$("#cekUserId").val()+'&ranNum=${ranNum}&u=${u}&moduleId=${moduleId}&moduleIdEss=${moduleIdEss}&vacancyToday='+"&userId="+$("#txtUserId").val()+"&statusTab="+${statusTab});
    }
    $("#gridRecruitment_filterText").keyup(function(event){
        if(event.keyCode === 13){
            $("#btnGridLeave_filterText").click();
        }
    });
    $('#gridRecruitment th').click(function (){
    });

    $('.btn-navLeave').click(function (){
       reloadGridLeave(this);
    });
    $('button.searchLeave').click(function (){
        postBackLeave();
    });
    $('#gridRecruitment_navRecordsToDispaly').change(function (){
       reloadGridLeave(this);
    });
    $('#btnViewLeave').click(function () {
    loadHome('/msservoapp/web/recruitment/transaction-vacancy?' + $("#FormGridLeave").serialize()
            + "&viewCreator=" + $("#ddlCreatorLeave").val()
            + "&docStatus=" + $("#ddlDocStatus").val()
            +"&creatorId="+$("#cekUserId").val()+"&adminId="+$("#cekUserId").val()+'&ranNum=${ranNum}&u=${u}&moduleId=${moduleId}&moduleIdEss=${moduleIdEss}&vacancyToday='+"&userId="+$("#txtUserId").val()+"&statusTab="+${statusTab});
    });
</script>
