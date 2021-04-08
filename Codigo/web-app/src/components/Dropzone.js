import "./Dropzone.css";
import React from "react";

const Dropzone = () => {
  const dragOver = (event) => {
    event.preventDefault();
  };

  const dragEnter = (event) => {
    event.preventDefault();
  };

  const dragLeave = (event) => {
    event.preventDefault();
  };

  const fileDrop = (event) => {
    event.stopPropagation();
    event.preventDefault();

    if (event.dataTransfer && event.dataTransfer.items) {
      var items = event.dataTransfer.items;

			console.log(event.dataTransfer);

      for (var i = 0; i < items.length; i++) {
        var item = items[i].webkitGetAsEntry();

        if (item) {
          if (item.isDirectory) {
            var directoryReader = item.createReader();
            directoryReader.readEntries(function (entries) {
              entries.forEach(function (entry) {
								console.log(entry);
                //_this.addDirectory(entry);
              });
            });
          } else {
            item.file(function (file) {
							console.log(file);
              //processFile([file], 0);
            });
          }
        }
      }
    } else {
      console.log("error");
    }
  };

  return (
    <div className="Dropzone">
      <div>
        <p className="title">React Drag and Drop Image Upload</p>
        <div className="content">
          <div className="container">
            <div
              className="drop-container"
              onDragOver={dragOver}
              onDragEnter={dragEnter}
              onDragLeave={dragLeave}
              onDrop={fileDrop}
            >
              <div className="drop-message">
                <i className="upload icon"></i>
                Drag and Drop files here or click to upload
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dropzone;
