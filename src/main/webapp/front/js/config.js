
var projectName = '疫情时期药物管理系统';
/**
 * 轮播图配置
 */
var swiper = {
	// 设定轮播容器宽度，支持像素和百分比
	width: '100%',
	height: '400px',
	// hover（悬停显示）
	// always（始终显示）
	// none（始终不显示）
	arrow: 'none',
	// default（左右切换）
	// updown（上下切换）
	// fade（渐隐渐显切换）
	anim: 'default',
	// 自动切换的时间间隔
	// 默认3000
	interval: 2000,
	// 指示器位置
	// inside（容器内部）
	// outside（容器外部）
	// none（不显示）
	indicator: 'outside'
}

/**
 * 个人中心菜单
 */
var centerMenu = [{
	name: '个人中心',
	url: '../' + localStorage.getItem('userTable') + '/center.html'
}, 
{
        name: '我的收藏',
        url: '../storeup/list.html'
}
]


var indexNav = [

{
	name: '医生',
	url: './pages/yisheng/list.html'
}, 
{
	name: '药品信息',
	url: './pages/yaopinxinxi/list.html'
}, 

{
	name: '药品资讯',
	url: './pages/news/list.html'
},
]

var adminurl =  "http://localhost:8080/ssmukk17/admin/dist/index.html";

var cartFlag = false

var chatFlag = false


chatFlag = true


var menu = [{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-news","buttons":["新增","查看","修改","删除"],"menu":"用户","menuJump":"列表","tableName":"yonghu"}],"menu":"用户管理"},{"child":[{"appFrontIcon":"cuIcon-camera","buttons":["新增","查看","修改","删除"],"menu":"医生","menuJump":"列表","tableName":"yisheng"}],"menu":"医生管理"},{"child":[{"appFrontIcon":"cuIcon-qrcode","buttons":["新增","查看","修改","删除"],"menu":"药品类别","menuJump":"列表","tableName":"yaopinleibie"}],"menu":"药品类别管理"},{"child":[{"appFrontIcon":"cuIcon-pay","buttons":["新增","查看","修改","删除"],"menu":"药品信息","menuJump":"列表","tableName":"yaopinxinxi"}],"menu":"药品信息管理"},{"child":[{"appFrontIcon":"cuIcon-flashlightopen","buttons":["查看","删除"],"menu":"药品购买","menuJump":"列表","tableName":"yaopingoumai"}],"menu":"药品购买管理"},{"child":[{"appFrontIcon":"cuIcon-similar","buttons":["查看","删除"],"menu":"在线咨询","menuJump":"列表","tableName":"zaixianzixun"}],"menu":"在线咨询管理"},{"child":[{"appFrontIcon":"cuIcon-service","buttons":["查看","修改","删除"],"menu":"在线客服","tableName":"chat"},{"appFrontIcon":"cuIcon-keyboard","buttons":["查看","修改","删除"],"menu":"轮播图管理","tableName":"config"},{"appFrontIcon":"cuIcon-news","buttons":["新增","查看","修改","删除"],"menu":"药品资讯","tableName":"news"}],"menu":"系统管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-medal","buttons":["查看","在线咨询"],"menu":"医生列表","menuJump":"列表","tableName":"yisheng"}],"menu":"医生模块"},{"child":[{"appFrontIcon":"cuIcon-attentionfavor","buttons":["查看","药品购买"],"menu":"药品信息列表","menuJump":"列表","tableName":"yaopinxinxi"}],"menu":"药品信息模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"管理员","tableName":"users"},{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-flashlightopen","buttons":["查看","删除","支付"],"menu":"药品购买","menuJump":"列表","tableName":"yaopingoumai"}],"menu":"药品购买管理"},{"child":[{"appFrontIcon":"cuIcon-similar","buttons":["查看","删除"],"menu":"在线咨询","menuJump":"列表","tableName":"zaixianzixun"}],"menu":"在线咨询管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-medal","buttons":["查看","在线咨询"],"menu":"医生列表","menuJump":"列表","tableName":"yisheng"}],"menu":"医生模块"},{"child":[{"appFrontIcon":"cuIcon-attentionfavor","buttons":["查看","药品购买"],"menu":"药品信息列表","menuJump":"列表","tableName":"yaopinxinxi"}],"menu":"药品信息模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"是","hasFrontRegister":"是","roleName":"用户","tableName":"yonghu"},{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-flashlightopen","buttons":["查看","删除","审核"],"menu":"药品购买","menuJump":"列表","tableName":"yaopingoumai"}],"menu":"药品购买管理"},{"child":[{"appFrontIcon":"cuIcon-similar","buttons":["查看","删除","审核"],"menu":"在线咨询","menuJump":"列表","tableName":"zaixianzixun"}],"menu":"在线咨询管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-medal","buttons":["查看","在线咨询"],"menu":"医生列表","menuJump":"列表","tableName":"yisheng"}],"menu":"医生模块"},{"child":[{"appFrontIcon":"cuIcon-attentionfavor","buttons":["查看","药品购买"],"menu":"药品信息列表","menuJump":"列表","tableName":"yaopinxinxi"}],"menu":"药品信息模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"是","hasFrontRegister":"是","roleName":"医生","tableName":"yisheng"}]


var isAuth = function (tableName,key) {
    let role = localStorage.getItem("userTable");
    let menus = menu;
    for(let i=0;i<menus.length;i++){
        if(menus[i].tableName==role){
            for(let j=0;j<menus[i].backMenu.length;j++){
                for(let k=0;k<menus[i].backMenu[j].child.length;k++){
                    if(tableName==menus[i].backMenu[j].child[k].tableName){
                        let buttons = menus[i].backMenu[j].child[k].buttons.join(',');
                        return buttons.indexOf(key) !== -1 || false
                    }
                }
            }
        }
    }
    return false;
}

var isFrontAuth = function (tableName,key) {
    let role = localStorage.getItem("userTable");
    let menus = menu;
    for(let i=0;i<menus.length;i++){
        if(menus[i].tableName==role){
            for(let j=0;j<menus[i].frontMenu.length;j++){
                for(let k=0;k<menus[i].frontMenu[j].child.length;k++){
                    if(tableName==menus[i].frontMenu[j].child[k].tableName){
                        let buttons = menus[i].frontMenu[j].child[k].buttons.join(',');
                        return buttons.indexOf(key) !== -1 || false
                    }
                }
            }
        }
    }
    return false;
}
