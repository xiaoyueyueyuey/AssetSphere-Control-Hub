package com.ach.admin.controller;


import com.ach.admin.customize.aop.accessLog.AccessLog;
import com.ach.admin.dto.post.PostDTO;
import com.ach.admin.entity.SysPostEntity;
import com.ach.admin.query.PostQuery;
import com.ach.admin.service.SysPostService;
import com.ach.common.base.BaseResponseData;
import com.ach.common.enums.common.BusinessTypeEnum;
import com.ach.domain.CommandInvoker;
import com.ach.domain.system.post.command.AddPostCommand;
import com.ach.domain.system.post.command.DeletePostCommand;
import com.ach.domain.system.post.command.UpdatePostCommand;
import com.ach.domain.system.post.handler.AddPostCommandHandler;
import com.ach.domain.system.post.handler.DeletePostCommandHandler;
import com.ach.domain.system.post.handler.UpdatePostCommandHandler;
import com.ach.infrastructure.base.BaseController;
import com.ach.infrastructure.page.PageCustomDTO;
import com.ach.infrastructure.utils.poi.CustomExcelUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 岗位信息操作处理
 *
 * @author ruoyi
 */
@Tag(name = "职位API", description = "职位相关的增删查改")
@RestController
@RequestMapping("/system/post")
@Validated
@RequiredArgsConstructor
public class SysPostController extends BaseController {
    @Resource
    private SysPostService sysPostService;
    @Resource
    private CommandInvoker commandInvoker;
    @Resource
    private AddPostCommandHandler addPostCommandHandler;
    @Resource
    private UpdatePostCommandHandler updatePostCommandHandler;
    @Resource
    private DeletePostCommandHandler deletePostCommandHandler;


    /**
     * 获取岗位列表
     */
    @Operation(summary = "职位列表")
    @PreAuthorize("@permission.has('system:post:list')")
    @GetMapping("/list")
    public BaseResponseData<PageCustomDTO<PostDTO>> list(PostQuery query) {
        Page<SysPostEntity> page = sysPostService.page(query.toPage(), query.toQueryWrapper());
        List<PostDTO> records = page.getRecords().stream().map(PostDTO::new).collect(Collectors.toList());
        PageCustomDTO<PostDTO> postDTOPageCustomDTO = new PageCustomDTO<>(records, page.getTotal());
        return BaseResponseData.ok(postDTOPageCustomDTO);
    }
    /**
     * 导出查询到的所有岗位信息到excel文件
     * @param response http响应
     * @param query 查询参数
     * @author Kevin Zhang
     * @date 2023-10-02
     */
    /**
     * 查询满足条件的所有岗位，不分页
     *
     * @param query 查询条件
     * @return 满足查询条件的岗位列表
     * @author Kevin Zhang
     * @date 2023-10-02
     */
    @Operation(summary = "职位列表导出")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('system:post:export')")
    @GetMapping("/excel")
    public void export(HttpServletResponse response, PostQuery query) {
        List<SysPostEntity> all = sysPostService.list(query.toQueryWrapper());
        List<PostDTO> records = all.stream().map(PostDTO::new).collect(Collectors.toList());
        CustomExcelUtil.writeToResponse(records, PostDTO.class, response);
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @Operation(summary = "职位详情")
    @PreAuthorize("@permission.has('system:post:query')")
    @GetMapping(value = "/{postId}")
    public BaseResponseData<PostDTO> getInfo(@PathVariable Long postId) {
        SysPostEntity byId = sysPostService.getById(postId);
        PostDTO postDTO = new PostDTO(byId);
        return BaseResponseData.ok(postDTO);
    }

    /**
     * 新增岗位
     */
    @Operation(summary = "添加职位")
    @PreAuthorize("@permission.has('system:post:add')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public BaseResponseData<Void> add(@RequestBody AddPostCommand addCommand) {
        Boolean execute = commandInvoker.execute(addPostCommandHandler, addCommand);
        if (!execute) {
            return BaseResponseData.fail();
        }

        return BaseResponseData.ok();
    }

    /**
     * 修改岗位
     */
    @Operation(summary = "修改职位")
    @PreAuthorize("@permission.has('system:post:edit')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public BaseResponseData<Void> edit(@RequestBody UpdatePostCommand updateCommand) {
        Boolean execute = commandInvoker.execute(updatePostCommandHandler, updateCommand);
        return BaseResponseData.ok();
    }

    /**
     * 删除岗位
     */
    @Operation(summary = "删除职位")
    @PreAuthorize("@permission.has('system:post:remove')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{postId}")
    public BaseResponseData<Void> remove(@PathVariable("postId") Long postId) {
        DeletePostCommand deletePostCommand = new DeletePostCommand();
        deletePostCommand.setPostId(postId);
        Boolean execute = commandInvoker.execute(deletePostCommandHandler, deletePostCommand);
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }

    /**
     * 批量删除岗位
     */
    @Operation(summary = "删除职位")
    @PreAuthorize("@permission.has('system:post:remove')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping
    public BaseResponseData<Void> remove(@RequestParam @NotNull @NotEmpty List<Long> ids) {
        //TODO
        return BaseResponseData.ok();
    }

}
