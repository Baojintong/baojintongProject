package bao.jt.tong.mongodbDao;


import bao.jt.tong.domain.UserEntity;

public interface UserDao {
    public void saveUser(UserEntity user);

    public UserEntity findUserByUserName(String userName);

    public void updateUser(UserEntity user);

    public void deleteUserById(Long id);
}
