// navBar_user
navBar_user = '<!-- navBar_user -->\
<nav class="navbar navbar-expand-lg bg-secondary px-5">\
    <div class="container-fluid">\
        <a class="navbar-brand" href="index.html">\
            <img src="statics/img/logo.png" alt="logo.png" height="24">\
        </a>\
        <ul class="navbar-nav">\
            <li class="nav-item">\
                <a class="nav-link" href="chatBox.html">\
                    <i class="bi bi-envelope"></i>\
                </a>\
            </li>\
            <li class="nav-item dropdown">\
                <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button"\
                    data-bs-toggle="dropdown" aria-expanded="false">\
                    <i class="bi bi-person-circle">會員中心</i>\
                </a>\
                <ul class="dropdown-menu">\
                    <li><a class="dropdown-item" href="center-basicInfo-view.html">基本資料</a></li>\
                    <li><a class="dropdown-item" href="center-myOrder-browse.html">我的訂單</a></li>\
                    <li><a class="dropdown-item" href="center-product-browse.html">商品管理</a></li>\
                    <li>\
                        <hr class="dropdown-divider">\
                    </li>\
                    <li><a class="dropdown-item" href="login.html">登出</a></li>\
                </ul>\
            </li>\
        </ul>\
    </div>\
</nav>';
document.write(navBar_user);
