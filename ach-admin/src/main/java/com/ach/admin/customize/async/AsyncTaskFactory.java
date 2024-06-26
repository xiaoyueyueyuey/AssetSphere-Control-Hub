package com.ach.admin.customize.async;


import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.ach.admin.entity.SysOperationLogEntity;
import com.ach.common.enums.common.LoginStatusEnum;
import com.ach.domain.CommandInvoker;
import com.ach.domain.system.log.login.command.AddLoginLogCommand;
import com.ach.domain.system.log.login.handler.AddLoginLogCommandHandler;
import com.ach.domain.system.log.operation.command.AddOperationLogCommand;
import com.ach.domain.system.log.operation.handler.AddOperationLogCommandHandler;
import com.ach.infrastructure.utils.ServletHolderUtil;
import com.ach.infrastructure.utils.ip.IpRegionUtil;
import com.ach.infrastructure.utils.ip.IpUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * 异步工厂（产生任务用）
 *
 */
@Slf4j
public class AsyncTaskFactory {

    private AsyncTaskFactory() {
    }

    /**
     * 记录登录信息,因为只有登录才会记录，所以不需要注解+AOP了
     *
     * @param username        用户名
     * @param loginStatusEnum 状态
     * @param message         消息
     * @return 任务task
     */

    public static Runnable loginInfoTask(final String username, final LoginStatusEnum loginStatusEnum, final String message) {
        // 优化一下这个类
        final UserAgent userAgent = UserAgent.parseUserAgentString(
                ServletHolderUtil.getRequest().getHeader("User-Agent"));
        // 获取客户端浏览器
        final String browser = userAgent.getBrowser() != null ? userAgent.getBrowser().getName() : "";
        final String ip = IpUtil.getClientIP(ServletHolderUtil.getRequest());//hutool工具包的javax和jakarta的包冲突 不支持
        final String address = IpRegionUtil.getBriefLocationByIp(ip);
        // 获取客户端操作系统
        final String os = userAgent.getOperatingSystem() != null ? userAgent.getOperatingSystem().getName() : "";
        log.info("ip: {}, address: {}, username: {}, loginStatusEnum: {}, message: {}", ip, address, username,
                loginStatusEnum, message);
        return () -> {
            // 封装命令
            AddLoginLogCommand command = new AddLoginLogCommand();
            command.setUsername(username);
            command.setIpAddress(ip);
            command.setLoginLocation(address);
            command.setBrowser(browser);
            command.setOperationSystem(os);
            command.setMsg(message);
            command.setLoginTime(DateUtil.date());
            command.setStatus(loginStatusEnum.getValue());
            // 插入数据
            AddLoginLogCommandHandler handler = SpringUtil.getBean(AddLoginLogCommandHandler.class);
            CommandInvoker invoker = SpringUtil.getBean(CommandInvoker.class);
            invoker.execute(handler, command);
        };
    }

    /**
     * 操作日志记录
     *
     * @param operationLog 操作日志信息
     * @return 任务task
     */
    public static Runnable recordOperationLog(final SysOperationLogEntity operationLog) {
        return () -> {
            // 远程查询操作地点
            operationLog.setOperatorLocation(IpRegionUtil.getBriefLocationByIp(operationLog.getOperatorIp()));
            AddOperationLogCommand addOperationLogCommand = new AddOperationLogCommand();
            BeanUtils.copyProperties(operationLog, addOperationLogCommand);
//            执行命令插入
            AddOperationLogCommandHandler handler = SpringUtil.getBean(AddOperationLogCommandHandler.class);
            CommandInvoker invoker = SpringUtil.getBean(CommandInvoker.class);
            invoker.execute(handler, addOperationLogCommand);

        };
    }

}
