package chun.xi.lin.shiro.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.springframework.util.StringUtils;

/**
 *  规则
 *    +资源字符串+权限位+实例ID
 *
 *  以+开头 中间通过+分割
 *
 *  权限：
 *     0 表示所有权限
 *     1 新增 0001
 *     2 修改 0010
 *     4 删除 0100
 *     8 查看 1000
 *
 *  如 +user+10 表示对资源user拥有修改/查看权限
 *
 *  不考虑一些异常情况
 *
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-26
 * <p>Version: 1.0
 * 这个定义的权限字符串格式：“+资源字符串+权限位+实例ID”
 */
public class BitPermission implements Permission {

    private String resourceIdentify;
    /**
     * 权限：0 表示所有权限；1 新增（二进制：0001）、2 修改（二进制：0010）、4 删除（二进制：0100）、8 查看（二进制：1000）；
     * 如 +user+10 表示对资源user拥有修改/查看权限。
     * */
    private int permissionBit;
    private String instanceId;

    public BitPermission(String permissionString) {
        // 分割你的权限设置，根据分割字符
        String[] array = permissionString.split("\\+");

        if(array.length > 1) {
            // 如果你的字符数组长度大于1，那么对应上面的格式的话，array[1]为资源字符串
            resourceIdentify = array[1];
        }

        if(StringUtils.isEmpty(resourceIdentify)) { // 加入得到的资源字符串为空，那么设置成*
            resourceIdentify = "*";
        }

        if(array.length > 2) {  // 与上面同理，这种可能有异常，不考虑
            permissionBit = Integer.valueOf(array[2]);
        }

        if(array.length > 3) {  // 同上
            instanceId = array[3];
        }

        if(StringUtils.isEmpty(instanceId)) {
            instanceId = "*";
        }

    }

    /**
     * 重写比较方法
     * */
    @Override
    public boolean implies(Permission p) {
        if(!(p instanceof BitPermission)) {
            return false;
        }
        BitPermission other = (BitPermission) p;

        if(!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
            // 验证资源不能为*，且验证资源不等于传入的资源，返回false
            return false;
        }

        if(!(this.permissionBit ==0 || (this.permissionBit & other.permissionBit) != 0)) {
            // 权限为不为0，且
            return false;
        }

        if(!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BitPermission{" +
                "resourceIdentify='" + resourceIdentify + '\'' +
                ", permissionBit=" + permissionBit +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
}
