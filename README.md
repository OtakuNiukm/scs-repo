# Spring Cloud Study

## Git 以及 IntelliJ IDEA 中 Git 和 GitHub 操作指南

### 目录

1. [Git 基本操作](#git-基本操作)
    - 初始化仓库
    - 添加文件到暂存区
    - 提交更改
    - 查看仓库状态
    - 查看提交历史
    - 创建与切换分支
    - 合并分支
    - 解决冲突
    - 推送到远程仓库
    - 拉取远程仓库代码
2. [IntelliJ IDEA 中的 Git 操作](#intellij-idea-中的-git-操作)
    - 配置 Git
    - 克隆远程仓库
    - 添加并提交代码
    - 分支管理
    - 合并分支与解决冲突
    - Push 和 Pull 操作
3. [GitHub 操作](#github-操作)
    - 创建仓库
    - Pull Request 流程
    - 合并分支
    - 删除仓库

## Git 基本操作

### 1. 初始化仓库

使用以下命令在项目目录中初始化一个 Git 仓库：

```bash
git init
```


### 2. 添加文件到暂存区

使用 `git add` 命令可以将文件添加到暂存区：

```bash
git add <file_name>        # 添加单个文件
git add .                  # 添加所有文件
```

### 3. 提交更改

提交暂存区中的文件到本地仓库，附带提交信息：

```bash
git commit -m "提交说明"
```

### 4. 查看仓库状态

使用 `git status` 查看当前工作区和暂存区的状态：

```bash
git status
```

### 5. 查看提交历史

使用 `git log` 查看提交历史：

```bash
git log
```

### 6. 创建与切换分支

创建新分支并切换到该分支：

```bash
git branch <branch_name>  # 创建新分支
git checkout <branch_name>  # 切换分支
```

### 7. 合并分支

将某个分支合并到当前分支：

```bash
git merge <branch_name>
```

### 8. 解决冲突

合并过程中可能出现冲突，解决冲突的步骤如下：

1. 打开冲突文件，手动解决冲突标记的部分。
2. 解决冲突后将文件重新添加到暂存区：

```bash
git add <conflicted_file>
```

3. 提交合并后的结果：

```bash
git commit
```

### 9. 推送到远程仓库

推送本地代码到远程仓库：

```bash
git push origin <branch_name>
```

### 10. 拉取远程仓库代码

从远程仓库拉取最新代码并与本地合并：

```bash
git pull origin <branch_name>
```

## IntelliJ IDEA 中的 Git 操作

### 1. 配置 Git

- 打开 IntelliJ IDEA，依次点击 `File` > `Settings` > `Version Control` > `Git`。
- 在 Git 路径栏中设置 Git 可执行文件路径，然后点击 `Test` 确保配置成功。

### 2. 克隆远程仓库

- 点击 `VCS` > `Get from Version Control`，在弹出的窗口中输入远程仓库地址。
- 选择项目保存路径，点击 `Clone`。

### 3. 添加并提交代码

- 右键项目或文件，选择 `Git` > `Add`，将文件添加到暂存区。
- 提交代码：点击右上角 `Commit` 按钮，填写提交信息后，点击 `Commit` 或 `Commit and Push` 直接提交并推送到远程仓库。

### 4. 分支管理

- 切换分支：点击右下角当前分支名称，选择要切换的分支。
- 创建分支：点击 `Git` > `New Branch`，输入分支名称，点击 `Create`。

### 5. 合并分支与解决冲突

- 合并分支：在分支列表中，选择 `Merge into Current` 进行分支合并。
- 如果发生冲突，IDEA 会自动提示并显示冲突文件，手动编辑文件后，使用 `Git` > `Resolve Conflicts` 完成合并。

### 6. Push 和 Pull 操作

- 推送代码：点击 `Git` > `Push`，选择要推送的分支，然后点击 `Push`。
- 拉取代码：点击 `Git` > `Pull`，选择拉取的分支，然后点击 `Pull`。

## GitHub 操作

### 1. 创建仓库

1. 登录 GitHub，点击右上角 `+` 图标，选择 `New repository`。
2. 填写仓库名称，选择公开或私有，点击 `Create repository`。

### 2. Pull Request 流程

1. 创建 Pull Request：
    - 在 GitHub 项目中，进入 `Pull Requests` 选项卡，点击 `New pull request`。
    - 选择要合并的分支，点击 `Create pull request`。
    - 填写 PR 说明，然后点击 `Create pull request`。

2. 审查与合并 Pull Request：
    - 其他开发者可以评论和审查代码。
    - 审查通过后，点击 `Merge pull request` 进行合并。

### 3. 合并分支

1. 在 GitHub 上进入仓库。
2. 打开 `Pull Requests`，选择要合并的 PR，点击 `Merge pull request`。
3. 选择合并方式：
    - `Create a merge commit`（默认）
    - `Squash and merge`
    - `Rebase and merge`

### 4. 删除仓库

1. 打开 GitHub 仓库，点击 `Settings`。
2. 滑动到页面底部，在 `Danger Zone` 中找到 `Delete this repository` 按钮。
3. 输入仓库名称确认删除，然后点击 `I understand the consequences, delete this repository`。