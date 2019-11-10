/**
 * FS包主要解决系统中的文件、文件夹的映射，将系统文件中的层级关系映射到Java运行
 * 内存中，方便读取、查找、过滤目标文件。同时，该包下所有文件全部支持Path对象的
 * 传值过程。
 * <p>
 * 一个文件可能是文件夹，也可能是文件，而且两者并不冲突，为了解决此问题，将所有
 * 文件全部抽象成{@code AbstractDirectory}和{@code AbstractFile},其
 * 中，{@code AbstractDirectory}代表一个目录或者文件夹，所有具有子文件的
 * 文件均是该抽象类的子类。对于{@code AbstractFile}, 代表这所有文件，所有
 * 文件都应该集成此类。
 * <p>
 * AbstractDirectory中包含迭代方法，并接收一个函数，用来遍历并读取目标文件。
 */
package io.github.mxudong.scanner.fs;