package com.ownproject.ServiceOrganizer.Services;

import com.ownproject.ServiceOrganizer.Mapper.RepRequestMapper;
import com.ownproject.ServiceOrganizer.Model.RepRequest;
import com.ownproject.ServiceOrganizer.Model.RepRequestForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepReqService {

    private RepRequestMapper repRequestMapper;

    public RepReqService(RepRequestMapper repRequestMapper) {
        this.repRequestMapper = repRequestMapper;
    }

    public List<RepRequest> getRepReqList(Integer userId) {
        return this.repRequestMapper.getAllRequests(userId);
    }


    public Integer addRepReq(RepRequestForm repReqform, Integer userId) {
        RepRequest repRequest = new RepRequest();
        repRequest.setClientName(repReqform.getClientName());
        repRequest.setLicencePlate(repReqform.getLicencePlate());
        repRequest.setVinNumber(repReqform.getVinNumber());
        repRequest.setDefectDescription(repReqform.getDefectDescription());
        repRequest.setUserId(userId);
        return this.repRequestMapper.insert(repRequest);
    }

    public Integer deleteRepReq(String clientName, Integer userid) {
      return  this.repRequestMapper.delete(clientName, userid);
    }

    public RepRequest getRepReq(String clientName, Integer userId){
        return this.repRequestMapper.getRepairRequest(clientName, userId);
    }

    public void updateNote(RepRequestForm repRequestForm){
        this.repRequestMapper.updateRepairRequest(repRequestForm.getClientName(), repRequestForm.getDefectDescription(), repRequestForm.getRepReqId());
    }


}
