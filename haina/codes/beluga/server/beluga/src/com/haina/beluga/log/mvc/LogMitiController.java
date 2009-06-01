package com.haina.beluga.log.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.haina.beluga.log.service.ILogService;

/**
 * @author:付翔.
 * @createDate:2007-7-25.
 * @classInfo:
 */

public class LogMitiController extends MultiActionController{
	
	private String intoSearchView;
	private String searchView;
	private ILogService logService;

	/**
	 * @param request
	 * @param response
	 * @return 
	 */
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView(getIntoSearchView());
	}
	public ModelAndView search(HttpServletRequest request,HttpServletResponse response,
			LogCommand cmd){
		request.removeAttribute("ph");
        int pageNumber;
        if (request.getParameter("page") != null
                && !"".equals(request.getParameter("page"))) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        } else {
            pageNumber = 1;
        }
//        PaginatedListHelper<Log> ph = new PaginatedListHelper<Log>();
//        //页面数据条数
//        ph.setObjectsPerPage(20);
//		if(null == cmd.getRemark()){
//			cmd =  (LogCommand) request.getSession().getAttribute("cmd");
//	    }else{
//			request.getSession().setAttribute("cmd", cmd);
//	    }
//		List<Log> logList=logService.findByPaginate(cmd, (pageNumber-1)*ph.getObjectsPerPage(), ph.getObjectsPerPage());
//		 int allSize = 20;//logService.getSizeByPaginate(cmd);
//		ph.setFullListSize(allSize);
//        ph.setList(logList);
//        ph.setPageNumber(pageNumber);
		return new ModelAndView(getSearchView());//.addObject("ph",ph);
	}
	public String getIntoSearchView() {
		
		return intoSearchView;
	}


	public void setIntoSearchView(String intoSearchView) {
		this.intoSearchView = intoSearchView;
	}
	public String getSearchView() {
		return searchView;
	}
	public void setSearchView(String searchView) {
		this.searchView = searchView;
	}
	public void setLogService(ILogService logService) {
		this.logService = logService;
	}
}
