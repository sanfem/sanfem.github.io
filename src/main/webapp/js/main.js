/**
 * 个人网站 - 主JavaScript文件
 */
document.addEventListener('DOMContentLoaded', function () {

    // 删除确认
    document.querySelectorAll('.btn-delete').forEach(function (btn) {
        btn.addEventListener('click', function (e) {
            if (!confirm('确定要删除吗？此操作不可撤销。')) {
                e.preventDefault();
            }
        });
    });

    // 导航栏当前页面高亮
    var currentPath = window.location.pathname;
    document.querySelectorAll('.navbar nav a').forEach(function (link) {
        if (link.getAttribute('href') && currentPath.indexOf(link.getAttribute('href')) !== -1) {
            link.classList.add('active');
        }
    });

    // 图片预览
    var photoInput = document.getElementById('photoInput');
    var photoPreview = document.getElementById('photoPreview');
    if (photoInput && photoPreview) {
        photoInput.addEventListener('change', function (e) {
            var file = e.target.files[0];
            if (file) {
                var reader = new FileReader();
                reader.onload = function (event) {
                    photoPreview.src = event.target.result;
                    photoPreview.style.display = 'block';
                };
                reader.readAsDataURL(file);
            }
        });
    }
});
