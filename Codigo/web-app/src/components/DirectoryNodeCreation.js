import React, { useEffect, useRef, useState } from "react";

const DirectoryNodeCreation = ({ parent, isCreatingNew, createChild }) => {
  const [title, setTitle] = useState("");
  const inputRef = useRef();

  const createNew = async (docParentId, docName, docType) => {
    await createChild(docParentId, docName, docType);

    isCreatingNew(false);
  };

  useEffect(() => {
    if (inputRef.current) {
      inputRef.current.focus();
    }
  }, []);

  return (
    <div className="list-item item">
      <i className={`folder icon`} />
      <div className="content">
        <div className="content-title">
          <div className="ui transparent input">
            <input
              ref={inputRef}
              type="text"
              placeholder="Novo diretório"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              onKeyPress={(e) =>
                e.key === "Enter" ? createNew(parent, title, "FOLDER") : null
              }
              onBlur={() => createNew(parent, title, "FOLDER")}
            />
          </div>
          <div className="description">{`Criando novo diretório`}</div>
        </div>
      </div>
    </div>
  );
};

export default DirectoryNodeCreation;
