package sigep.data.dto;

public class ChangePassDTO {
    private String newPass;
    private String previousPass;
    private Long usuarioId;

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getPreviousPass() {
        return previousPass;
    }

    public void setPreviousPass(String previousPass) {
        this.previousPass = previousPass;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
