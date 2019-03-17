/**
 * ************************************************easyUI方法***********************************************
 */

/**
 * @desc        easyUI分页样式,可继续添加
 * @author      zjc
 *
 * @param s     传入按钮
 */
function paginationCss(s) {
    //已存在样式
    if ($(".pagination-info:eq(1)").length > 0) {
        $("#buttonList").empty();
        addButton(s);
        //删除多个导出按钮,只保留一个
        $(".pagination-info #button_export:eq(1)").remove();
    } else {
        //克隆信息,更改样式
        var newEle = $(".pagination-info:eq(0)").clone(true);
        $(".pagination-info").after(newEle);
        $(".pagination-info:eq(0)").css({
            "float": "left"
        });

        $(".pagination-info:eq(1)").attr("id", "buttonList");
        $("#buttonList").empty();
        addButton(s);
    }

    //按需求添加按钮
    function addButton(s) {
        if (typeof s == "object") {
            for (var i = 0; i < s.length; i++) {
                //导出
                switch (s[i]) {
                    case 'export':
                        $(".pagination-info:eq(0)").append("<a href=\"javascript:void(0)\"  style='margin-left: 5px' title=\"导出\"  class=\"l-btn l-btn-small l-btn-plain easyui-tooltip\" group=\"\" id=\"button_export\"><span class=\"l-btn-left l-btn-icon-left\"><span class=\"l-btn-text\"></span><span class=\"l-btn-icon icon-print\"></span></span></a>")
                        break;
                    case 'insert':
                        $(".pagination-info:eq(1)").append("<a href=\"javascript:void(0)\" class=\"l-btn l-btn-small \" style='padding: 0 5px;margin-right: 5px' group=\"\" id=\"button_insert\"><span class=\"l-btn-left l-btn-icon-left\"><span class=\"l-btn-text\">添加</span><span class=\"l-btn-icon icon-add\">&nbsp;</span></span></a>")
                        break;
                    case 'update':
                        $(".pagination-info:eq(1)").append("<a href=\"javascript:void(0)\" class=\"l-btn l-btn-small \" style='padding: 0 5px;margin-right: 5px' group=\"\" id=\"button_update\"><span class=\"l-btn-left l-btn-icon-left\"><span class=\"l-btn-text\">修改</span><span class=\"l-btn-icon icon-edit\">&nbsp;</span></span></a>")
                        break;
                    case 'delete':
                        $(".pagination-info:eq(1)").append("<a href=\"javascript:void(0)\" class=\"l-btn l-btn-small \" style='padding: 0 5px;margin-right: 5px' group=\"\" id=\"button_delete\"><span class=\"l-btn-left l-btn-icon-left\"><span class=\"l-btn-text\">删除</span><span class=\"l-btn-icon icon-cancel\">&nbsp;</span></span></a>")
                        break;
                    case 'hang':
                        $(".pagination-info:eq(1)").append("<a href=\"javascript:void(0)\" class=\"l-btn l-btn-small \" style='padding: 0 5px;margin-right: 5px' group=\"\" id=\"button_hang\"><span class=\"l-btn-left l-btn-icon-left\"><span class=\"l-btn-text\">挂起</span><span class=\"l-btn-icon icon-ok\">&nbsp;</span></span></a>")
                        break;
                    case 'close':
                        $(".pagination-info:eq(1)").append("<a href=\"javascript:void(0)\" class=\"l-btn l-btn-small \" style='padding: 0 5px;margin-right: 5px' group=\"\" id=\"button_close\"><span class=\"l-btn-left l-btn-icon-left\"><span class=\"l-btn-text\">关闭</span><span class=\"l-btn-icon icon-no\">&nbsp;</span></span></a>")
                        break;
                    case 'adjust_permissions':
                        $(".pagination-info:eq(1)").append("<a href=\"javascript:void(0)\" class=\"l-btn l-btn-small \" style='padding: 0 5px;margin-right: 5px' group=\"\" id=\"button_adjust\"><span class=\"l-btn-left l-btn-icon-left\"><span class=\"l-btn-text\">调整权限</span><span class=\"l-btn-icon icon-man\">&nbsp;</span></span></a>")
                        break;
                    case 'adjust_role':
                        $(".pagination-info:eq(1)").append("<a href=\"javascript:void(0)\" class=\"l-btn l-btn-small \" style='padding: 0 5px;margin-right: 5px' group=\"\" id=\"button_adjust\"><span class=\"l-btn-left l-btn-icon-left\"><span class=\"l-btn-text\">调整角色</span><span class=\"l-btn-icon icon-man\">&nbsp;</span></span></a>")
                        break;
                    case 'details':
                        $(".pagination-info:eq(1)").append("<a href=\"javascript:void(0)\" class=\"l-btn l-btn-small \" style='padding: 0 5px;margin-right: 5px' group=\"\" id=\"button_details\"><span class=\"l-btn-left l-btn-icon-left\"><span class=\"l-btn-text\">详细信息</span><span class=\"l-btn-icon icon-more\">&nbsp;</span></span></a>")
                        break;
                }
            }
        }
    }

    //分页控件刷新悬停提示
    $(".datagrid-pager.pagination table tr td:last-child").empty().append("<a href=\"javascript:void(0)\" title=\"刷新\" class=\"l-btn l-btn-small l-btn-plain easyui-tooltip\" group=\"\" id=\"\"><span class=\"l-btn-left l-btn-icon-left\"><span class=\"l-btn-text l-btn-empty\">&nbsp;</span><span class=\"l-btn-icon pagination-load\">&nbsp;</span></span></a>")

}


/**
 * datebox清除按钮
 */
var buttons = $.extend([], $.fn.datebox.defaults.buttons);
buttons.splice(1, 0,
    {
        text: '清空',//按钮文本
        handler: function (target) {
            $("#" + target.id + "").datebox('setValue', "");//根据ID清空
            $("#" + target.id + "").datebox('hidePanel', "");
        }
    });


/**
 * @desc        easyUI获取表头,使用于导出
 * @author      zjc
 *
 * @param id    datagrid的id
 * @param n     传入起始列数
 * @returns {Array}
 */
function columnsBack(id, n) {
    var obj, arr = [],
        //获取列field,返回数组
        dataIndex = $("#" + id).datagrid("getColumnFields"),
        //获取列对象
        col = $("#" + id).datagrid('options').columns;
    col = col[0]
    for (var i = 0; i < col.length; i++) {
        for (var key in col[i]) {
            if (key == 'hidden' && col[i][key]) {
                dataIndex.remove(i);
            }
        }
    }
    //隐藏列会有td
    $(" .datagrid-header table tr td").each(function (r) {
        if (r >= n) {
            obj = {
                title: $(this).text(),
                dataIndex: dataIndex[r]
            };
            //隐藏列不添加到数组
            if (dataIndex[r] != undefined) {
                arr.push(obj);
            }
        }
    });
    return arr;
}


/**
 * @desc            表单赋值
 * @author          zjc
 *
 * @param id        datagrid id
 * @param formId    表单id
 */
function setDialogForm(id, formId, subNum) {
    var rowSelected = $("#" + id).datagrid('getSelected');
    if (subNum == undefined) {
        subNum = 0;
    }
    $("#" + formId + " input").each(function () {
        for (var key in rowSelected) {
            if ($(this).attr("id") != undefined && $(this).attr("class") != undefined) {
                if ($(this).attr("id").substr(subNum, $(this).attr("id").length) == key) {
                    //输入框
                    if ($(this).attr("class").indexOf("easyui-textbox") >= 0) {
                        $(this).textbox('setValue', rowSelected[key]);
                    }
                    //验证框
                    else if ($(this).attr("class").indexOf("easyui-validatebox") >= 0) {
                        $(this).val(rowSelected[key]);
                        //验证框先进行验证
                        $(this).validatebox('isValid');
                    }
                    //组合框
                    else if ($(this).attr("class").indexOf("easyui-combobox") >= 0) {
                        $(this).combobox('setValue', rowSelected[key]);
                    }
                    //combotree
                    else if ($(this).attr("class").indexOf("easyui-combotree") >= 0) {
                        $(this).combotree('setValue', rowSelected[key]);
                    }
                    //datebox
                    else if ($(this).attr("class").indexOf("easyui-datebox") >= 0) {
                        $(this).datebox('setValue', rowSelected[key]);
                    }
                }
            }

        }
    });
    $("#" + formId + " select").each(function () {
        for (var key in rowSelected) {
            if ($(this).attr("id") != undefined && $(this).attr("class") != undefined) {
                if ($(this).attr("id").substr(subNum, $(this).attr("id").length) == key) {
                    if ($(this).attr("class").indexOf("easyui-combobox") >= 0) {
                        $(this).combobox('setValue', rowSelected[key]);
                    }
                }
            }
        }
    });
}


/**
 * @desc                清空表单
 * @author              zjc
 *
 * @param formId        表单id
 */
function clearDialogForm(formId) {
    $("#" + formId + " input").each(function () {
        if ($(this).attr("id") != undefined && $(this).attr("class") != undefined) {
            if ($(this).attr("class").indexOf('easyui-textbox') >= 0) {
                $(this).textbox('setValue', '');
            }
            if ($(this).attr("class").indexOf('easyui-combotree') >= 0) {
                $(this).combotree('setValue', '');
            }
            if ($(this).attr("class").indexOf('easyui-validatebox') >= 0) {
                $(this).val('');
                //验证框先进行验证
                $(this).validatebox('isValid');
            }
            if ($(this).attr("class").indexOf('easyui-datebox') >= 0) {
                $(this).datebox('setValue', '');
            }
        }
    });
    $("#" + formId + " select").each(function () {
        if ($(this).attr("id") != undefined && $(this).attr("class") != undefined) {
            if ($(this).attr("class").indexOf('easyui-combobox') >= 0) {
                $(this).textbox('setValue', '');
            }
        }
    });

}


/**
 * @desc        收集表单验证信息
 * @author      zjc 2018/12/29
 *
 * @param formId    表单id
 */
function collectFormValid(formId) {
    var validRequiredText = '', validMessageText = '';
    $("#" + formId + "  input").each(function () {
        if ($(this).attr("id") != undefined && $(this).attr("class") != undefined) {
            if ($(this).attr("class").indexOf('easyui-validatebox') >= 0) {
                if (!$(this).validatebox('isValid')) {
                    //有必填属性并且为空
                    if ($(this).attr("required") == "required" && $(this).val() == '') {
                        validRequiredText += collectValidText($(this).attr("data-options")).replace("不能为空", "") + ' ';
                    } else if ($(this).attr("invalidMessage") != undefined) {
                        validMessageText += $(this).attr("invalidMessage").toString().replace("请填写正确的", "") + ' ';
                    }
                }
            }
            if ($(this).attr("class").indexOf('easyui-datebox') >= 0) {
                //验证框先进行验证
                if (!$(this).datebox('isValid')) {
                    //有必填属性并且为空
                    if ($(this).attr("required") == "required" && $(this).datebox('getValue') == '') {
                        validRequiredText += collectValidText($(this).attr("data-options")).replace("不能为空", "") + ' ';
                    } else if ($(this).attr("invalidMessage") != undefined) {
                        validMessageText += $(this).attr("invalidMessage").toString().replace("请填写正确的", "") + ' ';
                    }
                }
            }
            if ($(this).attr("class").indexOf('easyui-combotree') >= 0) {
                //验证框先进行验证
                if (!$(this).combotree('isValid')) {
                    //有必填属性并且为空
                    if ($(this).attr("required") == "required" && $(this).combotree('getValue') == '') {
                        validRequiredText += collectValidText($(this).attr("data-options")).replace("不能为空", "") + ' ';
                    }
                }
            }
        }
    });
    $("#" + formId + " select").each(function () {
        if ($(this).attr("id") != undefined && $(this).attr("class") != undefined) {
            if ($(this).attr("class").indexOf('easyui-combobox') >= 0) {
                if (!$(this).combobox('isValid')) {
                    if ($(this).attr("required") == "required" && $(this).combobox('getValue') == '') {
                        validRequiredText += collectValidText($(this).attr("data-options")).replace("不能为空", "") + ' ';
                    }
                }
            }
        }
    });
    //整理验证信息
    if (validRequiredText.trim() != '') {
        validRequiredText = validRequiredText + "不能为空 ";
    }
    if (validMessageText.trim() != '') {
        validMessageText = '请输入正确的' + validMessageText;
    }
    return validRequiredText + validMessageText;
}


/**
 * @desc        将验证提示拼接为json串,转成json获取信息
 * @author      zjc
 *
 * @param s     传入属性
 */
function collectValidText(s) {
    s = '{"' + s + '"}';
    s = s.replace(/,/g, '","').replace(/:/g, '":"').replace(/'/g, '');
    s = JSON.parse(s);
    return s.missingMessage;
}

/**
 * **************************************************easyUI认证**************************************************
 */



$.extend($.fn.validatebox.defaults.rules, {
    idcard: {// 验证身份证
        validator: function (value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message: '身份证号码格式不正确'
    },
    minLength: {
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: '请输入至少（2）个字符.'
    },
    length: {
        validator: function (value, param) {
            var len = $.trim(value).length;
            return len >= param[0] && len <= param[1];
        },
        message: "输入内容长度必须介于{0}和{1}之间."
    },
    phone: {// 验证电话号码
        validator: function (value) {
            return /^((\d2,3\d2,3)|(\d{3}\-))?(0\d2,30\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '电话号码格式不正确,请使用下面格式:020-88888888'
    },
    mobile: {// 验证手机号码
        validator: function (value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message: '手机号码格式不正确'
    },
    intOrFloat: {// 验证整数或小数
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '请输入数字，并确保格式正确'
    },
    currency: {// 验证货币
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '货币格式不正确'
    },
    qq: {// 验证QQ,从10000开始
        validator: function (value) {
            return /^[1-9]\d{4,9}$/i.test(value);
        },
        message: 'QQ号码格式不正确'
    },
    integer: {// 验证整数 可正负数
        validator: function (value) {
            //return /^[+]?[1-9]+\d*$/i.test(value);

            return /^([+]?[0-9])|([-]?[0-9])+\d*$/i.test(value);
        },
        message: '请输入整数'
    },
    age: {// 验证年龄
        validator: function (value) {
            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
        },
        message: '年龄必须是0到120之间的整数'
    },

    chinese: {// 验证中文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value);
        },
        message: '请输入中文'
    },
    english: {// 验证英语
        validator: function (value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message: '请输入英文'
    },
    unnormal: {// 验证是否包含空格和非法字符
        validator: function (value) {
            return /.+/i.test(value);
        },
        message: '输入值不能为空和包含其他非法字符'
    },
    username: {// 验证用户名
        validator: function (value) {
            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
        },
        message: '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
    },
    faxno: {// 验证传真
        validator: function (value) {
            //            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
            return /^((\d2,3\d2,3)|(\d{3}\-))?(0\d2,30\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '传真号码不正确'
    },
    zip: {// 验证邮政编码
        validator: function (value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message: '邮政编码格式不正确'
    },
    ip: {// 验证IP地址
        validator: function (value) {
            return /d+.d+.d+.d+/i.test(value);
        },
        message: 'IP地址格式不正确'
    },
    name: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message: '请输入姓名'
    },
    date: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            //格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
        },
        message: '清输入合适的日期格式'
    },
    msn: {
        validator: function (value) {
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
        },
        message: '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    same: {
        validator: function (value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
                return $("#" + param[0]).val() == value;
            } else {
                return true;
            }
        },
        message: '两次输入的密码不一致！'
    },
    /*必须和某个字段相等*/
    equalTo: {
        validator: function (value, param) {
            return $(param[0]).val() == value;
        },
        message: '字段不匹配'
    }
});


/**
 * ************************************************其他基础通用方法***********************************************
 */


/**
 * @desc 发送POST请求跳转到指定页面,模拟form表单
 * @author zjc
 *
 * @param URL
 * @param PARAMS
 * @returns {Element}
 */
function httpPost(URL, PARAMS) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}


/**
 * @desc    +''处理
 * @author  zjc
 *
 *
 * @param q
 *            //字符串含有","
 * @returns {string}
 */
function addQuotationMarksS(q) {
    var arr = q.split(",");
    var str = '';
    for (var i = 0; i < arr.length; i++) {
        str += "'" + arr[i] + "'" + ",";
    }
    str = str.substr(0, str.length - 1);
    return str;
}

/**
 * @desc            加""处理
 * @author          zjc 2019/01/02
 *
 * @param q
 * @returns {string}
 */
function addDoubleQuotes(q) {
    var arr = q.split(",");
    var str = '';
    for (var i = 0; i < arr.length; i++) {
        str += '"' + arr[i] + '"' + ',';
    }
    str = str.substr(0, str.length - 1);
    return str;
}


/**
 * @desc        css样式转number
 * @author      zjc
 *
 * @param c     css样式值
 */
function cssParseInt(c) {
    //像素
    if (c.indexOf("px")) {
        c = parseInt(c.replace("px", ""));
    } else {
        c = parseInt(c);
    }
    return c;
}

/**
 *删除数组指定下标或指定对象
 */
Array.prototype.remove = function (obj) {
    for (var i = 0; i < this.length; i++) {
        var temp = this[i];
        if (!isNaN(obj)) {
            temp = i;
        }
        if (temp == obj) {
            for (var j = i; j < this.length; j++) {
                this[j] = this[j + 1];
            }
            this.length = this.length - 1;
        }
    }
}


/**
 * @desc        给input添加键盘监听事件，只读属性
 * @author      zjc
 *
 * @param id    元素id
 */
function inputKeyDown(id) {
    $("#" + id).attr("readonly", "readonly");
    $("#" + id).on("keydown", function (e) {
        if (e.keyCode == 8 || e.keyCode == 46) {
            $(this).val("")
            $(this).next().val("")
        }
    })
}


/**
 * @desc            数组去重,新建一新数组，遍历传入数组，值不在新数组就push进该新数组中,IE8以下不支持数组的indexOf方法
 * @param array     传入数组
 * @returns {Array}
 */
function uniqueArray(array) {
    var temp = []; //一个新的临时数组
    for (var i = 0; i < array.length; i++) {
        if (temp.indexOf(array[i]) == -1) {
            temp.push(array[i]);
        }
    }
    return temp;
}


/**
 * @desc            数据库字段在前端转变,模糊查询like,如错误用户
 * @author          zjc
 *
 * @param str       传入字符
 * @param id        元素id
 */
function inputLike(str, id) {
    var validArr = [];
    for (var i = 0; i < str.length; i++) {
        for (var j = 0; j < str.length - i; j++) {
            validArr.push(str.substr(i, j + 1));
        }
    }
    console.log(validArr);
    if (validArr.indexOf($("#" + id).textbox('getValue')) >= 0) {
        $("#frm_userName").textbox('setValue', "undefined");
        console.log($("#frm_userName").textbox('getValue'))
    } else {
        $("#" + id).attr("null", false);
    }
}


/**
 * @desc    验证重复
 * @author  zjc
 *
 * @param id    页面id
 * @param msg   验证信息
 * @constructor
 */
function VerificationDuplicate(id, msg) {
    var v1 = $("input[id=" + id + "][repeat=true]").val();
    var v2 = $("input[id=" + id + "][repeat!=true]").val();
    if (v1 == v2) {
        return true;
    }
    $.messager.alert('提示信息', msg, 'warning');
    return false;
}


/**
 * @desc        标签id为表字段,获取表单内容并返回js对象,适用于右侧表单查询和dialog新增修改的请求参数,待完善
 * @author      zjc 2019/01/02
 *
 * @param id    表单id
 * @param num   id字段截取,区分右侧查询和表单新增修改
 */
function getFromContent(id, num) {
    var obj = {}, jsonStr = "";
    if (num == undefined) {
        num = 0;
    }
    $("#" + id + "  input").each(function () {
        //id和class同时存在
        if ($(this).attr("id") != undefined && $(this).attr("class") != undefined) {
            //validatebox
            if ($(this).attr("class").indexOf('easyui-validatebox') >= 0) {
                var str = $(this).attr("id").toString().split("_");
                var s = '';
                //转换成驼峰命名法
                //A_B_C_和A_B_C情况
                if ((str.length == 4 && str[3] == '') || (str.length == 3 && str[2] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase() + str[2].substring(0, 1).toLocaleUpperCase() + str[2].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).val());
                }
                //A_B_和A_B
                else if ((str.length == 3 && str[2] == '') || (str.length == 2 && str[1] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).val());
                }
                //A_和A
                else if ((str.length == 2 && str[1] == '') || (str.length == 1 && str[0] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).val());
                }
                jsonStr += s + ' , ';
            }
            //textbox
            if ($(this).attr("class").indexOf('easyui-textbox') >= 0) {
                var str = $(this).attr("id").toString().split("_");
                var s = '';
                //转换成驼峰命名法
                //A_B_C_和A_B_C情况
                if ((str.length == 4 && str[3] == '') || (str.length == 3 && str[2] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase() + str[2].substring(0, 1).toLocaleUpperCase() + str[2].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).textbox('getValue'));
                }
                //A_B_和A_B
                else if ((str.length == 3 && str[2] == '') || (str.length == 2 && str[1] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).textbox('getValue'));
                }
                //A_和A
                else if ((str.length == 2 && str[1] == '') || (str.length == 1 && str[0] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).textbox('getValue'));
                }
                jsonStr += s + ' , ';
            }
            //datebox
            if ($(this).attr("class").indexOf('easyui-datebox') >= 0) {

                var str = $(this).attr("id").toString().split("_");
                var s = '';
                //转换成驼峰命名法
                //A_B_C_和A_B_C情况
                if ((str.length == 4 && str[3] == '') || (str.length == 3 && str[2] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase() + str[2].substring(0, 1).toLocaleUpperCase() + str[2].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).datebox('getValue'));
                }
                //A_B_和A_B
                else if ((str.length == 3 && str[2] == '') || (str.length == 2 && str[1] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).datebox('getValue'));
                }
                //A_和A
                else if ((str.length == 2 && str[1] == '') || (str.length == 1 && str[0] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).datebox('getValue'));
                }
                jsonStr += s + ' , ';
            }
            //combotree
            if ($(this).attr("class").indexOf('easyui-combotree') >= 0) {
                var str = $(this).attr("id").toString().split("_");
                var s = '';
                //转换成驼峰命名法
                //A_B_C_和A_B_C情况
                if ((str.length == 4 && str[3] == '') || (str.length == 3 && str[2] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase() + str[2].substring(0, 1).toLocaleUpperCase() + str[2].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).combotree('getValue'));
                }
                //A_B_和A_B
                else if ((str.length == 3 && str[2] == '') || (str.length == 2 && str[1] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).combotree('getValue'));
                }
                //A_和A
                else if ((str.length == 2 && str[1] == '') || (str.length == 1 && str[0] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).combotree('getValue'));
                }
                jsonStr += s + ' , ';
            }
            //combobox
            if ($(this).attr("class").indexOf('easyui-combobox') >= 0) {
                var str = $(this).attr("id").toString().split("_");
                var s = '';
                //转换成驼峰命名法
                //A_B_C_和A_B_C情况
                if ((str.length == 4 && str[3] == '') || (str.length == 3 && str[2] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase() + str[2].substring(0, 1).toLocaleUpperCase() + str[2].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).combobox('getValue'));
                }
                //A_B_和A_B
                else if ((str.length == 3 && str[2] == '') || (str.length == 2 && str[1] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).combobox('getValue'));
                }
                //A_和A
                else if ((str.length == 2 && str[1] == '') || (str.length == 1 && str[0] != '')) {
                    s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).combobox('getValue'));
                }
                jsonStr += s + ' , ';
            }
        }
    });
    $("#" + id + "  select").each(function () {
        //combobox
        if ($(this).attr("class").indexOf('easyui-combobox') >= 0) {
            var str = $(this).attr("id").toString().split("_");
            var s = '';
            //转换成驼峰命名法
            //A_B_C_和A_B_C情况
            if ((str.length == 4 && str[3] == '') || (str.length == 3 && str[2] != '')) {
                s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase() + str[2].substring(0, 1).toLocaleUpperCase() + str[2].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).combobox('getValue'));
            }
            //A_B_和A_B
            else if ((str.length == 3 && str[2] == '') || (str.length == 2 && str[1] != '')) {
                s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase() + str[1].substring(0, 1).toLocaleUpperCase() + str[1].substring(1).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).combobox('getValue'));
            }
            //A_和A
            else if ((str.length == 2 && str[1] == '') || (str.length == 1 && str[0] != '')) {
                s = addDoubleQuotes(str[0].substr(num, str[0].length).toLocaleLowerCase()) + ':' + addDoubleQuotes($(this).combobox('getValue'));
            }
            jsonStr += s + ' , ';
        }
    });
    //去除最后一个','
    jsonStr = jsonStr.substr(0, jsonStr.lastIndexOf(','));
    jsonStr = "{" + jsonStr + "}";
    // console.log(jsonStr)
    // console.log(JSON.parse(jsonStr))
    return JSON.parse(jsonStr)
}


/**
 * @desc            对象拷贝
 *
 * @param obj       传入对象
 * @returns {{}}
 */
var cloneObj = function (obj) {
    var newObj = {};
    if (obj instanceof Array) {
        newObj = [];
    }
    for (var key in obj) {
        var val = obj[key];
        //newObj[key] = typeof val === 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。
        newObj[key] = typeof val === 'object' ? cloneObj(val) : val;
    }
    return newObj;
};


/**
 * @desc  js获取cookie
 *
 * @param cookie_name
 */
function getCookie(cookie_name) {
    var allcookies = document.cookie;
    var cookie_pos = allcookies.indexOf(cookie_name);   //索引的长度
    // 如果找到了索引，就代表cookie存在，
    // 反之，就说明不存在。
    if (cookie_pos != -1) {
        // 把cookie_pos放在值的开始，只要给值加1即可。
        cookie_pos += cookie_name.length + 1;      //这里容易出问题，所以请大家参考的时候自己好好研究一下
        var cookie_end = allcookies.indexOf(";", cookie_pos);

        if (cookie_end == -1) {
            cookie_end = allcookies.length;
        }

        var value = unescape(allcookies.substring(cookie_pos, cookie_end));         //这里就可以得到你想要的cookie的值了。。。
    }
    return value;
}


/**
 * @desc  js获取cookie
 *
 * @param cookie_name
 */
function getCookie2(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

    if (arr = document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

/**
 * 获取跳转的url并截取参数
 * 2019/02/17
 *
 * @returns {Object}    返回数组对象
 * @constructor
 */
function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}