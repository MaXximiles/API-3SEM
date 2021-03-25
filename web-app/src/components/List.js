import React from "react";
import ListItem from "./ListItem";

const List = ({ content }) => {
  const renderedContent = content.map((item) => {
    return <ListItem key={item.id} name={item.name} content={item.content} />;
  });

  return <div className="folder-list ui list">{renderedContent}</div>;
};

export default List;
