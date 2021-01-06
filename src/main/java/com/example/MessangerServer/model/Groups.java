package com.example.MessangerServer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GROUPS")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private String groupName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employees> userListGroup = new ArrayList<>();
    @OneToMany(targetEntity=GroupMessages.class, mappedBy="messagesgroup")
    private List<GroupMessages> groupsMessage = new ArrayList<>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Employees> getUserListGroup() {
        return userListGroup;
    }

    public void setUserListGroup(List<Employees> userListGroup) {
        this.userListGroup = userListGroup;
    }

    public List<GroupMessages> getGroupsMessage() {
        return groupsMessage;
    }

    public void setGroupsMessage(List<GroupMessages> groupsMessage) {
        this.groupsMessage = groupsMessage;
    }
}
