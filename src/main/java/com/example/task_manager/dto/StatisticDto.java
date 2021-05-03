package com.example.task_manager.dto;

public class StatisticDto {
    private Long countTask;
    private Long countGetTask;
    private Long countSendTask;
    private Long countCompleteTask;
    private Long countDesignTask;
    private Long countDevelopTask;
    private Long countBusinessTask;
    private Long countAnalyseTask;

    public Long getCountTask() {
        return this.getCountGetTask()+this.getCountSendTask();
    }
    public Long getCountGetTask() {
        return countGetTask;
    }

    public void setCountGetTask(Long countGetTask) {
        this.countGetTask = countGetTask;
    }

    public Long getCountSendTask() {
        return countSendTask;
    }

    public void setCountSendTask(Long countSendTask) {
        this.countSendTask = countSendTask;
    }

    public Long getCountCompleteTask() {
        return countCompleteTask;
    }

    public void setCountCompleteTask(Long countCompleteTask) {
        this.countCompleteTask = countCompleteTask;
    }

    public Long getCountDesignTask() {
        return countDesignTask;
    }

    public void setCountDesignTask(Long countDesignTask) {
        this.countDesignTask = countDesignTask;
    }

    public Long getCountDevelopTask() {
        return countDevelopTask;
    }

    public void setCountDevelopTask(Long countDevelopTask) {
        this.countDevelopTask = countDevelopTask;
    }

    public Long getCountBusinessTask() {
        return countBusinessTask;
    }

    public void setCountBusinessTask(Long countBusinessTask) {
        this.countBusinessTask = countBusinessTask;
    }

    public Long getCountAnalyseTask() {
        return countAnalyseTask;
    }

    public void setCountAnalyseTask(Long countAnalyseTask) {
        this.countAnalyseTask = countAnalyseTask;
    }
}
