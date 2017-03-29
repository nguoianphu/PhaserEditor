// Generated by Phaser Editor v1.4.0 (Phaser v2.6.2)
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
/**
 * Level.
 */
var Level = (function (_super) {
    __extends(Level, _super);
    function Level() {
        return _super.call(this) || this;
    }
    Level.prototype.init = function () {
        this.scale.scaleMode = Phaser.ScaleManager.SHOW_ALL;
        this.scale.pageAlignHorizontally = true;
        this.scale.pageAlignVertically = true;
        this.stage.backgroundColor = '#ffffff';
    };
    Level.prototype.preload = function () {
    };
    ;
    Level.prototype.create = function () {
        // to change this code: Canvas editor > Configuration > Editor > userCode > Create
        this.addHelloWorldText();
    };
    /* state-methods-begin */
    Level.prototype.addHelloWorldText = function () {
        this.add.text(100, 100, "hello world!", { fill: "#000" });
    };
    return Level;
}(Phaser.State));
/* --- end generated code --- */
// -- user code here --