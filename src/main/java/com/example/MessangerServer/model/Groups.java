package com.example.MessangerServer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "GROUPS")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private String groupName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Employees> userListGroup;
    @OneToMany(targetEntity=GroupMessages.class, mappedBy="messagesgroup")
    private Set<GroupMessages> groupsMessage;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Employees> getUserListGroup() {
        return userListGroup;
    }

    public void setUserListGroup(Set<Employees> userListGroup) {
        this.userListGroup = userListGroup;
    }

    public Set<GroupMessages> getGroupsMessage() {
        return groupsMessage;
    }

    public void setGroupsMessage(Set<GroupMessages> groupsMessage) {
        this.groupsMessage = groupsMessage;
    }
}
