package com.example.MessangerServer.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "GROUPS")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private String groupName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Employee> userSetGroup;
    @OneToMany(targetEntity = GroupMessages.class, mappedBy = "messagesgroup")
    private Set<GroupMessages> groupsMessage;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Employee> getUserListGroup() {
        return userSetGroup;
    }

    public void setUserListGroup(Set<Employee> userListGroup) {
        this.userSetGroup = userListGroup;
    }

    public Set<GroupMessages> getGroupsMessage() {
        return groupsMessage;
    }

    public void setGroupsMessage(Set<GroupMessages> groupsMessage) {
        this.groupsMessage = groupsMessage;
    }
}
