package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.CommentModel;

public class CommentMaper {
    public static CommentDTO fromCommetDTO(CommentModel commentModel) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPk(commentModel.getPk());
        commentDTO.setAuthor(commentModel.getAuthor());
        commentDTO.setText(commentModel.getText());
        commentDTO.setCreatedAt(commentModel.getCreateAt());
        commentDTO.setAuthorFirstName(commentDTO.getAuthorFirstName());
        commentDTO.setAuthor(commentModel.getAuthor());
        commentDTO.setAuthorImage(commentDTO.getAuthorImage());
        return commentDTO;
    }

    public static CommentModel toCommentModel(CommentDTO commentDTO) {
        CommentModel commentModel = new CommentModel();
        commentModel.setPk(commentModel.getPk());
        commentModel.setCreateAt(commentDTO.getCreatedAt());
        commentModel.setText(commentDTO.getText());
        commentModel.setAuthor(commentDTO.getAuthor());
        return commentModel;
    }
}