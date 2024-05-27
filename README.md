## ChaoxingQuestionParser

### 功能
学校思政课期末前，如果提供练习的话，可以使用这个快捷导出学习通可识别格式。

当使用APP创建课程后，可以单独将题库导入，多次重做，查缺补漏，带着你的小伙伴一起卷。

相比于直接CV，此工具将会统一格式，更加直观和美观。

### 使用方法
1. 先完成题目，然后在网页版点开已完成作业
2. 右键，查看网页源代码
3. CV一下
4. 修改HtmlParser的path（保存路径）
5. 运行本插件

### 导出示例
```
【单选题】1.（     ）、英国、德国工人运动的兴起，标志着现代无产阶级作为独立的政治力量登上了历史舞台。
A. 美国
B. 法国
C. 中国
D. 俄国
答案：B

【多选题】34.大学生在学习马克思主义理论的过程中，要有正确的态度和科学的方法（     ）。
A. 努力学习和掌握马克思主义的基本立场观点方法
B. 努力学习和掌握马克思主义中国化时代化的理论成果
C. 坚持理论联系实际的马克思主义学风
D. 自觉将马克思主义内化于心、外化于行
答案：ABCD
```

### 特别鸣谢
ChatGPT提供了核心的代码和思路，本人只稍作优化。