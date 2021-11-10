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

    public List<RepRequest> getUnscheduledRepReqList() {
        return this.repRequestMapper.getAllUnScheduledRequests();
    }
    public List<RepRequest> getAllRequestsById(Integer id){
        return this.repRequestMapper.getAllRequestsByUserId(id);
    }


    public Integer addRepReq(RepRequestForm repReqform, Integer userId) {
        RepRequest repRequest = new RepRequest();
        repRequest.setClientName(repReqform.getClientName());
        repRequest.setLicencePlate(repReqform.getLicencePlate());
        repRequest.setVinNumber(repReqform.getVinNumber());
        repRequest.setDefectDescription(repReqform.getDefectDescription());
        repRequest.setUserId(userId);
        repRequest.setPartsOrdered(false);
        repRequest.setScheduled(false);
        repRequest.setFinished(false);
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

    public void setOrderedStatus(Integer repReqId, Boolean isPartsOrdered){
        this.repRequestMapper.setOrderedStatus(repReqId,isPartsOrdered);
    }
    public void setScheduledStatus(Integer repReqId, Boolean isScheduled){
        this.repRequestMapper.setScheduledStatus(repReqId,isScheduled);
    }
    public void setFinishedStatus(Integer repReqId, Boolean isFinished){
        this.repRequestMapper.setFinishedStatus(repReqId,isFinished);
    }
    public RepRequest getRepReqById(Integer id){
        return this.repRequestMapper.getRepReqById(id);
    }


}
