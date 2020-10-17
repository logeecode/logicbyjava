@RequestMapping(value = {"/CNHCHC8033"}, method = RequestMethod.GET)
    private RedirectView getCNHCHC8033(HttpServletRequest request, HttpSession currentSession) {
        ranNumber = ComFunc.generateRanNum();
        if (!getTokenContainer(requestURL.getParameter("token-user")).equals(requestURL.getParameter("token-user"))) {
            System.out.println("[" + ranNumber + "] - [" + oFormat.format(new Date()) + "] Parameter : Error Null, paramNilai kosong");
            return new RedirectView("/msservoapp/web/application/employee-null?statusValidate=3");
        } else {
            System.out.println("[" + ranNumber + "] - [" + oFormat.format(new Date()) + "] Parameter : " + request.getParameter("paramNilai"));
            String paramNilai = request.getParameter("paramNilai");
            String userName = setSessionBaseFromTxt_API(request);
            String moduleId = (String) requestSession.getAttribute("buki_node");
            System.out.println("node= " + moduleId);
            return new RedirectView("/msservoapp/web/benefit/benefittransaction-grid?u=" + userName + "&moduleId=" + moduleId + "&statusvalid=1");
        }
    }

     @RequestMapping(value = {"/benefittransaction-grid"}, method = RequestMethod.GET)
    private ModelAndView getBenefitTransactionGrid(HttpServletRequest request,
            @RequestParam(value = "sortasc", required=false) String byasc,
            @RequestParam(value = "sortdesc", required=false) String bydsc,
            @RequestParam(value = "nav", required=false) String navpage,
            @RequestParam(value = "ranNum", required=false) String ranNum,
            @RequestParam(value = "u", required = false) String uName,
            @RequestParam(value = "moduleId", required = false) String moduleId,
            @RequestParam(value = "moduleIdEss", required = false) String moduleIdEss) throws UnsupportedEncodingException {
        ModelAndView oEmpViews = new ModelAndView();
        RestTemplate restTemplate = new RestTemplate();
        ranNumber = ranNum == null ? ranNumber : ranNum;
        HttpSession currentSession = request.getSession(false) == null ? request.getSession() : request.getSession(false);


        String userId = uName;
        moduleIdEss = "CNHCHC8033";
        Object oListStatusUser = new Object();
        if(moduleIdEss.equals("CNHCHC4006")){
            oListStatusUser = restTemplate.getForObject(getURLBaseFromTxt_API(currentSession) + "/employee/opendataobj-statusadminess?intCurrentPage=1&intPageSize=0&strWhereCond=" + userId + "&strSortBy=" + moduleId, Object.class);
        }else{
            oListStatusUser = restTemplate.getForObject(getURLBaseFromTxt_API(currentSession) + "/employee/opendataobj-statusadmin?intCurrentPage=1&intPageSize=0&strWhereCond=" + userId + "&strSortBy=" + moduleId, Object.class);
        }
//        Object oListStatusUser = restTemplate.getForObject(getURLBaseFromTxt_API(currentSession) + "/employee/opendataobj-statusadmin?intCurrentPage=1&intPageSize=0&strWhereCond=" + userId + "&strSortBy=" + moduleId, Object.class);
        Map<String, Object> objParams = (Map<String, Object>) (Object) oListStatusUser;
        String UserType = (String) objParams.get("AdminType");
        String UserCabang = (String) objParams.get("CodeCabangAdmin");

        if ("1".equals(UserType) || "2".equals(UserType)) {
            oEmpViews = new ModelAndView("body/Benefit/benefit_transaction");
            //STATUS USER
            if(moduleIdEss.equals("CNHCHC4006")){
                oListStatusUser = restTemplate.getForObject(getURLBaseFromTxt_API(currentSession) + "/employee/opendataobj-statusadminess?intCurrentPage=1&intPageSize=0&strWhereCond=" + userId + "&strSortBy=" + moduleId, Object.class);
            }else{
                oListStatusUser = restTemplate.getForObject(getURLBaseFromTxt_API(currentSession) + "/employee/opendataobj-statusadmin?intCurrentPage=1&intPageSize=0&strWhereCond=" + userId + "&strSortBy=" + moduleId, Object.class);
            }
//            oListStatusUser = restTemplate.getForObject(getURLBaseFromTxt_API(currentSession) + "/employee/opendataobj-statusadmin?intCurrentPage=1&intPageSize=0&strWhereCond=" + userId + "&strSortBy=" + moduleId, Object.class);
            oEmpViews.addObject("user", oListStatusUser);

            String encodedWhereCond = StringUtils.isEmpty(request.getParameter("gridemployeeheader_filtertext")) ? "" : "(a.employee_id like '%" + request.getParameter("gridemployeeheader_filtertext") + "%' OR employee_id2 like '%"
                    + request.getParameter("gridemployeeheader_filtertext")
                    + "%' OR cabang like '%" + request.getParameter("gridemployeeheader_filtertext")
                    + "%' OR employee_name LIKE '%"
                    + request.getParameter("gridemployeeheader_filtertext") + "%')";

            String pageSize = StringUtils.isEmpty(request.getParameter("gridleave_navrecordstodisplay")) ? "25" : request.getParameter("gridleave_navrecordstodisplay");
            String currentPage = StringUtils.isEmpty(request.getParameter("gridLeave_navPageJumpTo")) ? "1" : request.getParameter("gridLeave_navPageJumpTo");
            String sortBy = ""; //StringUtils.isEmpty(byasc) ? (StringUtils.isEmpty(bydsc) ? "" : bydsc + " DESC") : byasc + " ASC";
            String totalPage = StringUtils.isEmpty(request.getParameter("gridleave_pagetotal")) ? "" : request.getParameter("gridleave_pagetotal");

            if (navpage != null) {
                switch (navpage) {
                    case "next": {
                        currentPage = Integer.toString(Integer.parseInt(currentPage) + 1);
                    }
                    break;
                    case "prev": {
                        currentPage = Integer.toString(Integer.parseInt(currentPage) - 1);
                    }
                    break;
                    case "first": {
                        currentPage = "1";
                    }
                    break;
                    case "last": {
                        currentPage = totalPage;
                    }
                    break;

                }
            }
            String errorMessage = "";
            try {
//                GenericDataGrid oLeaveGrid = restTemplate.getForObject(getURLBaseFromTxt_API(currentSession)+"/employee/spemployeepaging?go="+currentPage+"&fr="+pageSize+"&in="+encodedWhereCond+"&by="+sortBy, GenericDataGrid.class);
                GenericDataGrid oLeaveGrid = restTemplate.getForObject(getURLBaseFromTxt_API(currentSession) + "/benefit/spemployeestatusadminbenefit?go=" + currentPage + "&fr=" + pageSize + "&in=" + encodedWhereCond + "&by=" + sortBy + "&AdminId=" + userId + "&ModuleType="+moduleId+"&UserType="+UserType, GenericDataGrid.class);
                oEmpViews.addObject("employeeList", oLeaveGrid.getResultDataList());
                oEmpViews.addObject("first", oLeaveGrid.getPageProperties().getFirstPage());
                oEmpViews.addObject("last", oLeaveGrid.getPageProperties().getLastPage());
                oEmpViews.addObject("currentpage", oLeaveGrid.getPageProperties().getPageCurrent());
                oEmpViews.addObject("totalpage", (oLeaveGrid.getPageProperties().getTotalItems() + oLeaveGrid.getPageProperties().getPageSize() - 1) / oLeaveGrid.getPageProperties().getPageSize());
                oEmpViews.addObject("totalrecords", oLeaveGrid.getPageProperties().getTotalItems());
                oEmpViews.addObject("last", oLeaveGrid.getPageProperties().getLastPage());
                oEmpViews.addObject("pagesize", oLeaveGrid.getPageProperties().getPageSize());
            } catch (Exception a) {
                errorMessage = a.getMessage();
                System.out.println("Error while fetching Object : " + a.getMessage());
            }
            oEmpViews.addObject("sortby", StringUtils.isEmpty(byasc) ? (StringUtils.isEmpty(bydsc) ? "" : bydsc + " DESC") : byasc + " ASC");
            oEmpViews.addObject("wherecond", request.getParameter("gridemployeeheader_filtertext"));
            oEmpViews.addObject("errorcode", request.getParameter("gridemployeeheader_filtertext"));
            oEmpViews.addObject("grid", 1);
            oEmpViews.addObject("ranNum", ranNumber);
            oEmpViews.addObject("u", uName);
            oEmpViews.addObject("moduleId", moduleId);
            oEmpViews.addObject("moduleIdEss", moduleIdEss);

        }else{
            oEmpViews = new ModelAndView("body/employee_null");
            oEmpViews.addObject("statusValidate",2);
        }
        String moduleIdAudit = "";
        if(moduleId == moduleIdEss){
            moduleIdAudit = moduleId;
        }else{
            moduleIdAudit = moduleIdEss;
        }
        ApplicationMstModule oModule = restTemplate.getForObject(getURLBaseFromTxt_API(currentSession) + "/application/opendata-module/" + moduleIdAudit, ApplicationMstModule.class);
        oEmpViews.addObject("module", oModule);
        Object oUrlWeb = restTemplate.getForObject(getURLBaseFromTxt_API(currentSession) + "/application/urlweb-module?moduleId=" + moduleIdAudit, Object.class);
        if(oUrlWeb != null){
            Map<String, Object> objUrlWeb = (Map<String, Object>) (Object) oUrlWeb;
            urlWeb = (String) objUrlWeb.get("urlWeb");
        }
        oEmpViews.addObject("urlWeb", urlWeb);
        return oEmpViews;
    }
