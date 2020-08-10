package com.vyara.fantasy.data.entities;

import com.vyara.fantasy.data.entities.base.BaseEntity;
import com.vyara.fantasy.data.entities.secondary.Comment;
import com.vyara.fantasy.data.entities.secondary.Rating;
import com.vyara.fantasy.data.entities.secondary.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Question> questions;

    @OneToMany(mappedBy = "user", targetEntity = ShortStory.class,cascade=CascadeType.REMOVE)
    private List<ShortStory> shortStories;

    //SpringSecurity:

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
