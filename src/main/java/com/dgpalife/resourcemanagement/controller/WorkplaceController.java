package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.NetworkRoom;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.model.Workplace;
import com.dgpalife.resourcemanagement.service.NetworkRoomService;
import com.dgpalife.resourcemanagement.service.WorkplaceService;
import com.dgpalife.resourcemanagement.vo.LayuiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/setting/workplace")
public class WorkplaceController {

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private NetworkRoomService networkRoomService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/setting/workplace/index";
    }

    /**
     * 异步加载职场列表
     * @param pageno
     * @param pagesize
     * @return
     */
    @ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize){

        AjaxResult result = new AjaxResult();

        try{
            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            Page<Workplace> page = workplaceService.selectWorkplaceList(params);
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 异步加载职场下的机房列表
     * @param workplaceId
     * @return
     */
    @ResponseBody
    @RequestMapping("/showNetworkRoom")
    public Object showNetworkRoom(Long workplaceId){

        AjaxResult result = new AjaxResult();

        try{
            List<NetworkRoom> networkRoomList = networkRoomService.selectNetworkRoomListByWorkplaceId(workplaceId);
            result.setDatas(networkRoomList);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toAddWorkplace")
    public String toAddWorkplace(){
        return "/setting/workplace/addWorkplace";
    }

    /**
     * 异步添加
     * @param workplace
     * @return
     */
    @ResponseBody
    @RequestMapping("/doAddWorkplace")
    public Object doAddWorkplace(Workplace workplace, HttpSession session){

        AjaxResult result = new AjaxResult();

        try{
            User user = (User)session.getAttribute(Const.LOGIN_USER);
            workplace.setCreatorId(user.getId());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            workplace.setCreateTime(sdf.format(date));
            int count = workplaceService.saveWorkplace(workplace);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("添加异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toUpdateWorkplace/{id}")
    public String toUpdateWorkplace(@PathVariable Long id,Model model){
        Workplace workplace = workplaceService.selectWorkplaceById(id);
        model.addAttribute("workplace",workplace);
        return "/setting/workplace/updateWorkplace";
    }

    /**
     * 异步更新
     * @param workplace
     * @return
     */
    @ResponseBody
    @RequestMapping("/doUpdateWorkplace")
    public Object doUpdateWorkplace(Workplace workplace){
        AjaxResult result = new AjaxResult();
        try{
            int count = workplaceService.updateWorkplace(workplace);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("更新错误，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 异步删除职场数据，并级联删除关联的机房信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/doDeleteWorkplaceAndNetworkRoom")
    public Object doDeleteWorkplaceAndNetworkRoom(Long id){
        AjaxResult result = new AjaxResult();
        try{
            workplaceService.deleteWorkplaceAndNetworkRoomByWorkplaceId(id);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("更新错误，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toAddNetworkRoom")
    public String toAddNetworkRoom(){
        return "/setting/workplace/addNetworkRoom";
    }

    @ResponseBody
    @RequestMapping("/showTable")
    public Object showTable(){

        LayuiVO layuiVO = new LayuiVO();

        try {
            List<Object> workplaceList = workplaceService.selectWorkplace();
            int count = workplaceService.selectCount();
            layuiVO.setData(workplaceList);
            layuiVO.setCount(count);
            layuiVO.setCode(0);
        }catch (Exception e){
            e.printStackTrace();
            layuiVO.setCode(1);
            layuiVO.setMsg("查询异常，请联系管理员处理");
        }

        return layuiVO;

    }
}
